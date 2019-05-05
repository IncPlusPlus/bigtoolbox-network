﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ManagedNativeWifi
{
	/// <summary>
	/// Wireless LAN information on available network
	/// </summary>
	public class AvailableNetworkPack
	{
		/// <summary>
		/// Associated wireless interface information
		/// </summary>
		public InterfaceInfo Interface { get; }

		/// <summary>
		/// SSID (maximum 32 bytes)
		/// </summary>
		public NetworkIdentifier Ssid { get; }

		/// <summary>
		/// BSS network type
		/// </summary>
		public BssType BssType { get; }

		/// <summary>
		/// Signal quality (0-100)
		/// </summary>
		public int SignalQuality { get; }

		/// <summary>
		/// Whether security is enabled on this network
		/// </summary>
		public bool IsSecurityEnabled { get; }

		/// <summary>
		/// Associated wireless profile name
		/// </summary>
		public string ProfileName { get; }

		/// <summary>
		/// Constructor
		/// </summary>
		public AvailableNetworkPack(
			InterfaceInfo interfaceInfo,
			NetworkIdentifier ssid,
			BssType bssType,
			int signalQuality,
			bool isSecurityEnabled,
			string profileName)
		{
			this.Interface = interfaceInfo;
			this.Ssid = ssid;
			this.BssType = bssType;
			this.SignalQuality = signalQuality;
			this.IsSecurityEnabled = isSecurityEnabled;
			this.ProfileName = profileName;
		}
	}

	/// <summary>
	/// Wireless LAN information on available network and group of associated BSS networks
	/// </summary>
	public class AvailableNetworkGroupPack : AvailableNetworkPack
	{
		/// <summary>
		/// Associated BSS networks information
		/// </summary>
		public IReadOnlyCollection<BssNetworkPack> BssNetworks => Array.AsReadOnly(_bssNetworks);
		private readonly BssNetworkPack[] _bssNetworks;

		/// <summary>
		/// Link quality of associated BSS network which is the highest link quality
		/// </summary>
		public int LinkQuality { get; }

		/// <summary>
		/// Frequency (KHz) of associated BSS network which has the highest link quality
		/// </summary>
		public int Frequency { get; }

		/// <summary>
		/// Frequency band (GHz) of associated BSS network which has the highest link quality
		/// </summary>
		public float Band { get; }

		/// <summary>
		/// Channel of associated BSS network which has the highest link quality
		/// </summary>
		public int Channel { get; }

		/// <summary>
		/// Constructor
		/// </summary>
		public AvailableNetworkGroupPack(
			InterfaceInfo interfaceInfo,
			NetworkIdentifier ssid,
			BssType bssType,
			int signalQuality,
			bool isSecurityEnabled,
			string profileName,
			IEnumerable<BssNetworkPack> bssNetworks) : base(
				interfaceInfo: interfaceInfo,
				ssid: ssid,
				bssType: bssType,
				signalQuality: signalQuality,
				isSecurityEnabled: isSecurityEnabled,
				profileName: profileName)
		{
			this._bssNetworks = bssNetworks.OrderByDescending(x => x.LinkQuality).ToArray();

			var highestLinkQualityNetwork = _bssNetworks.FirstOrDefault();
			if (highestLinkQualityNetwork != null)
			{
				LinkQuality = highestLinkQualityNetwork.LinkQuality;
				Frequency = highestLinkQualityNetwork.Frequency;
				Band = highestLinkQualityNetwork.Band;
				Channel = highestLinkQualityNetwork.Channel;
			}
		}
	}
}