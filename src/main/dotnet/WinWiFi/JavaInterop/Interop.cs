using System;
using SimpleWifi;

namespace JavaInterop
{
    public class Interop
    {
        private static Wifi wifi;
        public static void Start()
        {
            wifi = new Wifi();
            string rawInput = "";
            int input;
            Console.WriteLine((int)ResponseToJava.SESSION_OPENED);
            do
            {
                rawInput = Console.ReadLine();
                if (!int.TryParse(rawInput, out input))
                {
                    Console.WriteLine("Warning! Received garbage input: " + rawInput);
                }

                Execute(input);
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
            if (wifi.Scan())
            {
                Console.WriteLine(ResponseToJava.SCAN_COMPLETED);
            }
            else
            {
                Console.WriteLine(ResponseToJava.SCAN_FAILED);
            }
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
            Console.WriteLine((int)ResponseToJava.SESSION_CLOSED);
        }
    }
}