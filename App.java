public class App {
    public static void main(String[] args) throws Exception {
        Btree a = new Btree();
        int[] valores = {10, 5, 12, 3, 8, 15, 18};

        for (int valor : valores) {
            a.add(valor);
        }
          
    }
}
