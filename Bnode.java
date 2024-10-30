public class Bnode {
    private int x;
    private Bnode esq, dir;
    int critical = 0;
    private int altura;
   
   

    public Bnode(int valor) {
        x = valor;
        esq = null;
        dir = null;
        altura = 1 ;
    }

    public void add(int valor) {
      
        if (valor > x) {
            if (dir != null) dir.add(valor);
            else dir = new Bnode(valor);
        } else if(valor < x) {
            if (esq != null) esq.add(valor);
            else esq = new Bnode(valor);
        }else{
             System.out.println("Valor já inserido: "+valor);
        }

       

        int fatorBalanceamento = calcularFatorDeBalanceamento(this);

        if (calcularFatorDeBalanceamento(this) == -2 || calcularFatorDeBalanceamento(this) == 2 ) {
            System.out.println("Fator de balanceamento do nó " + valor + ": " + 0);
           
        }
       
      
        System.out.println("Fator de balanceamento do nó " + x + ": " + fatorBalanceamento);
      
        if(fatorBalanceamento == -2 || fatorBalanceamento == 2 ){
            System.out.println("Nó crítico: "+ x);
            System.out.println("Balanceando ...");
            atualizarAltura(this);
            balancear(this);
        }
    }
  
    public void atualizarAltura(Bnode no) {
        int alturaEsq = 0, alturaDir = 0;
        if (no.esq != null) alturaEsq = no.esq.altura;
        if (no.dir != null) alturaDir = no.dir.altura;
        no.altura = Math.max(alturaEsq, alturaDir) + 1;
    }
    public Bnode balancear(Bnode no) {
        int fatorBalanceamento = calcularFatorDeBalanceamento(no);

       
        if (fatorBalanceamento < -1) {
            if (calcularFatorDeBalanceamento(no.dir) > 0) {
                System.out.println("Rotação a direita ...");
                no.dir = rotacaoDireita(no.dir);
            }
            System.out.println("Rotação a esquerda ...");
            return rotacaoEsquerda(no); 
        }
        if (fatorBalanceamento > 1) {
            if (calcularFatorDeBalanceamento(no.esq) < 0) {
                System.out.println("Rotação a esquerda ...");
                no.esq = rotacaoEsquerda(no.esq); 
            }
            System.out.println("Rotação a direita ...");
            return rotacaoDireita(no); 
        }

        return no; 
    }
    public int calcularAltura(Bnode node) {
        if (node == null) {
            return -1;
        } else {
            int alturaEsq = calcularAltura(node.esq);
            int alturaDir = calcularAltura(node.dir);
         
            return 1 + Math.max(alturaEsq, alturaDir);
            
        }
    }

    public int calcularFatorDeBalanceamento(Bnode node) {
        if (node == null) {
            return 0;
        } else {
            int alturaEsq = calcularAltura(node.esq);
            int alturaDir = calcularAltura(node.dir);
            return alturaEsq - alturaDir;
        }
    }

    public Bnode rotacaoDireita(Bnode node) {
        if (node == null || node.esq == null) {
            return node;
        }
        Bnode novoRaiz = node.esq;
        node.esq = novoRaiz.dir;
        novoRaiz.dir = node;
        return novoRaiz;
    }

    public Bnode rotacaoEsquerda(Bnode node) {
        if (node == null || node.dir == null) {
            return node;
        }
        Bnode novoRaiz = node.dir;
        node.dir = novoRaiz.esq;
        novoRaiz.esq = node;
        return novoRaiz;
    }

    public Bnode rotacaoDuplaEsquerda(Bnode node) {
        if (node == null || node.dir == null) {
            return node;
        }
        node.dir = rotacaoDireita(node.dir);
        return rotacaoEsquerda(node);
    }
    
    public Bnode rotacaoDuplaDireita(Bnode node) {
        if (node == null || node.esq == null) {
            return node;
        }
        node.esq = rotacaoEsquerda(node.esq);
        return rotacaoDireita(node);
    }

    public void show() {
        int fatorBalanceamento = (this == this) ? 0 : calcularFatorDeBalanceamento(this);
        System.out.println("Valores da árvore balanceados: " + x + ", Fator de Balanceamento: " + fatorBalanceamento);
        if (dir != null) dir.show();
        if (esq != null) esq.show();
      
    }

    public int size() {
        int e = 0, d = 0;
        if (esq != null) e = esq.size();
        if (dir != null) d = dir.size();
        return 1 + e + d;
    }

    public int soma() {
        int e = 0, d = 0;
        if (esq != null) e = esq.soma();
        if (dir != null) d = dir.soma();
        return x + e + d;
    }
}
