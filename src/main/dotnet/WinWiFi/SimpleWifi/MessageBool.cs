namespace SimpleWifi
{
    public class MessageBool
    {
        public string message;
        public bool success;

        public MessageBool(bool success, string message)
        {
            this.success = success;
            this.message = message;
        }

        public bool successful()
        {
            return success;
        }

        public string getMessage()
        {
            return message;
        }
    }
}