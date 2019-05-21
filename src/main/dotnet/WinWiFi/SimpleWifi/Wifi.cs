﻿using SimpleWifi.Win32;
using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
using System.Runtime.InteropServices;
using SimpleWifi.Win32.Interop;
using NotifCodeACM = SimpleWifi.Win32.Interop.WlanNotificationCodeAcm;
using NotifCodeMSM = SimpleWifi.Win32.Interop.WlanNotificationCodeMsm;

namespace SimpleWifi
{
    public class Wifi
    {
        public event EventHandler<WifiStatusEventArgs> ConnectionStatusChanged;

        private WlanClient _client;
        private WifiStatus _connectionStatus;
        private bool _isConnectionStatusSet = false;
        private bool? _scanSuccessful = null;
        public bool NoWifiAvailable = false;

        public Wifi()
        {
            _client = new WlanClient();
            NoWifiAvailable = _client.NoWifiAvailable;
            if (_client.NoWifiAvailable)
                return;

            foreach (var inte in _client.Interfaces)
                inte.WlanNotification += inte_WlanNotification;
        }

        public bool Scan()
        {
            _scanSuccessful = null;
            foreach (WlanInterface wlanInterface in _client.Interfaces)
            {
                try
                {
                    wlanInterface.WlanNotification += WlanNotificationChanged;
                    wlanInterface.Scan();
                    while (_scanSuccessful == null)
                    {
                    }

                    wlanInterface.WlanNotification -= WlanNotificationChanged;
                    if (_scanSuccessful == false)
                    {
                        return false;
                    }
                }
                catch (System.ComponentModel.Win32Exception e)
                {
                    if (e.Message.Equals(
                        "The wireless local area network interface is powered down and doesn't support the requested operation")
                    )
                    {
                        Console.WriteLine(e.Message);
                    }
                    else
                    {
                        Console.WriteLine(e);
                    }
                    return false;
                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                    return false;
                }
            }

            return true;
        }

        private void WlanNotificationChanged(WlanNotificationData e)
        {
            if (e.NotificationCode.Equals(WlanNotificationCodeAcm.ScanComplete))
            {
                _scanSuccessful = true;
            }
            else if(e.NotificationCode.Equals(WlanNotificationCodeAcm.ScanFail))
            {
                _scanSuccessful = false;
            }
        }

        /// <summary>
        /// Returns a list over all available access points
        /// </summary>
        public List<AccessPoint> GetAccessPoints()
        {
            List<AccessPoint> accessPoints = new List<AccessPoint>();
            if (_client.NoWifiAvailable)
                return accessPoints;

            foreach (WlanInterface wlanIface in _client.Interfaces)
            {
                WlanAvailableNetwork[] rawNetworks = wlanIface.GetAvailableNetworkList(0);
                List<WlanAvailableNetwork> networks = new List<WlanAvailableNetwork>();

                // Remove network entries without profile name if one exist with a profile name.
                foreach (WlanAvailableNetwork network in rawNetworks)
                {
                    bool hasProfileName = !string.IsNullOrEmpty(network.profileName);
                    bool anotherInstanceWithProfileExists = rawNetworks
                        .Where(n => n.Equals(network) && !string.IsNullOrEmpty(n.profileName)).Any();

                    if (!anotherInstanceWithProfileExists || hasProfileName)
                        networks.Add(network);
                }

                foreach (WlanAvailableNetwork network in networks)
                {
                    accessPoints.Add(new AccessPoint(wlanIface, network));
                }
            }

            return accessPoints;
        }

        /// <summary>
        /// Disconnect all wifi interfaces
        /// </summary>
        public void Disconnect()
        {
            if (_client.NoWifiAvailable)
                return;

            foreach (WlanInterface wlanIface in _client.Interfaces)
            {
                wlanIface.Disconnect();
            }
        }

        public WifiStatus ConnectionStatus
        {
            get
            {
                if (!_isConnectionStatusSet)
                    ConnectionStatus = GetForcedConnectionStatus();

                return _connectionStatus;
            }
            private set
            {
                _isConnectionStatusSet = true;
                _connectionStatus = value;
            }
        }

        private void inte_WlanNotification(WlanNotificationData notifyData)
        {
            if (notifyData.notificationSource == WlanNotificationSource.ACM &&
                (NotifCodeACM) notifyData.NotificationCode == NotifCodeACM.Disconnected)
                OnConnectionStatusChanged(WifiStatus.Disconnected);
            else if (notifyData.notificationSource == WlanNotificationSource.MSM &&
                     (NotifCodeMSM) notifyData.NotificationCode == NotifCodeMSM.Connected)
                OnConnectionStatusChanged(WifiStatus.Connected);
        }

        private void OnConnectionStatusChanged(WifiStatus newStatus)
        {
            ConnectionStatus = newStatus;

            if (ConnectionStatusChanged != null)
                ConnectionStatusChanged(this, new WifiStatusEventArgs(newStatus));
        }

        // I don't like this method, it's slow, ugly and should be refactored ASAP.
        private WifiStatus GetForcedConnectionStatus()
        {
            if (NoWifiAvailable)
                return WifiStatus.Disconnected;

            bool connected = false;

            foreach (var i in _client.Interfaces)
            {
                try
                {
                    var a = i.CurrentConnection; // Current connection throws an exception if disconnected.
                    connected = true;
                }
                catch
                {
                }
            }

            if (connected)
                return WifiStatus.Connected;
            else
                return WifiStatus.Disconnected;
        }
    }

    public class WifiStatusEventArgs : EventArgs
    {
        public WifiStatus NewStatus { get; private set; }

        internal WifiStatusEventArgs(WifiStatus status) : base()
        {
            this.NewStatus = status;
        }
    }

    public enum WifiStatus
    {
        Disconnected,
        Connected
    }
}