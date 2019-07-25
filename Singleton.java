public class Singleton {

    static class ConnectionPool {
        private static ConnectionPool instance;

        private int minConnections;
        private int maxConnections;

        private ConnectionPool(final int minConnections, final int maxConnections) {
            this.minConnections = minConnections;
            this.maxConnections = maxConnections;
        }

        public int getMinConnections() {
            return this.minConnections;
        }

        public int getMaxConnections() {
            return this.maxConnections;
        }

        public static synchronized ConnectionPool getInstance() {
            if (instance == null) {
                instance = new ConnectionPool(1, 100);
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        // A single instance per JVM and a single point of access
        final ConnectionPool instance = ConnectionPool.getInstance();
        System.out.println(instance.getMinConnections());
        System.out.println(instance.getMaxConnections());
    }
}
