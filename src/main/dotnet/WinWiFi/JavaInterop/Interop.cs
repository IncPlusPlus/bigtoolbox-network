using System;
using SimpleWifi;
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
                if (!int.TryParse(rawInput, out input))
                {
                    Console.WriteLine("Warning! Received garbage input: " + rawInput);
                }
                else
                {
                    Execute(input);
                }
            } while (input != 0);
        }

        private static void Execute(int input)
        {
            switch (input)
            {
                case (int) JavaRequest.SCAN:
                    Scan();
                    break;
                case (int) JavaRequest.LIST_APS:
                    ListAPs();
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
            Write(wifi.Scan() ? SCAN_COMPLETED : SCAN_FAILED);
            WriteStr(wifi._lastFailReason);
        }

        private static void ListAPs()
        {
        }

        private static void ListAPsDetail()
        {
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

        private static void Write(ResponseToJava responseToJava)
        {
            Console.WriteLine((int)responseToJava);
        }

        private static void WriteStr(string response)
        {
            Console.WriteLine(response);
        }
    }
}