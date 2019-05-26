using System;
using System.Collections.Generic;
using System.Linq;
using SimpleWifi;
using Newtonsoft.Json;
using SimpleWifi.Win32;
using SimpleWifi.Win32.Interop;
using static JavaInterop.ResponseToJava;

namespace JavaInterop
{
    public static class Interop
    {
        private static Wifi wifi;

        public static void Start()
        {
            wifi = new Wifi();
            string rawInput = "";
            int input;
            Write(SESSION_OPENED);
            do
            {
                wifi._lastFailReason = "";
                rawInput = Console.ReadLine();
                if (!string.IsNullOrEmpty(rawInput))
                {
                    if (!int.TryParse(rawInput, out input))
                    {
                        string s = new string(rawInput.ToCharArray());
                        Console.WriteLine("Warning! Received garbage input: " + "\"" + s + "\"");
                    }
                    else
                    {
                        Execute(input);
                    }
                }else{Console.WriteLine("Warning! Received null or empty input.");}
            } while (true);
        }

        private static void Execute(int input)
        {
            switch (input)
            {
                case (int) JavaRequest.SCAN:
                    Scan();
                    break;
                case (int) JavaRequest.LIST_APS_DETAIL:
                    ListAPsDetail();
                    break;
                case (int) JavaRequest.DISCONNECT:
                    Disconnect();
                    break;
                case (int) JavaRequest.CONNECT_TO_AP:
                    ConnectToAP();
                    break;
                case (int) JavaRequest.CLOSE_SESSION:
                    CloseSession();
                    break;
                default:
                    Console.WriteLine("Warning! Received bad command: " + input);
                    break;
            }
        }

        private static void Scan()
        {
            string jsonout = JsonConvert.SerializeObject(wifi.Scan());
//            Write(wifi.Scan().successful() ? SCAN_COMPLETED : SCAN_FAILED);
            WriteStr(jsonout);
        }

        private static void ListAPsDetail()
        {
            var accessPoints = List();
            List<AccessPoint.JAccessPoint> jAccessPoints = new List<AccessPoint.JAccessPoint>();

            foreach (AccessPoint accessPoint in accessPoints)
            {
                jAccessPoints.Add(accessPoint.GetJAccessPoint());
            }
            Console.WriteLine(JsonConvert.SerializeObject(jAccessPoints));
            
        }

        private static void Disconnect()
        {
        }

        private static void ConnectToAP()
        {
        }

        private static void CloseSession()
        {
            Write(SESSION_CLOSED);
            Environment.Exit(0);
        }
        
        private static IEnumerable<AccessPoint> List()
        {
            IEnumerable<AccessPoint> accessPoints = wifi.GetAccessPoints().OrderByDescending(ap => ap.SignalStrength);

            return accessPoints;
        }

        private static void Write(ResponseToJava responseToJava)
        {
            Console.WriteLine((int) responseToJava);
        }

        private static void WriteStr(string response)
        {
            Console.WriteLine(response);
        }
    }
}