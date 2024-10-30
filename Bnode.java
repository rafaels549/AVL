public class Bnode {
    private int x;
    private Bnode esq, dir;
    int critical = 0;
    private int altura;
    private int fatorBalanceamento;

   

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

       

         fatorBalanceamento = calcularFatorDeBalanceamento(this);
       
      
        System.out.println("Fator de balanceamento do nó " + x + ": " + fatorBalanceamento);
      
        if(fatorBalanceamento == -2 || fatorBalanceamento == 2 ){
            System.out.println("Nó crítico: "+ x);
            System.out.println("Balanceando ...");
              
            balancear(this);
        }
        if (this.esq != null) {
            int fatorEsq = calcularFatorDeBalanceamento(this.esq);
            System.out.println("Fator de balanceamento do nó " + this.esq.x + ": " + fatorEsq);
        }
    
        if (this.dir != null) {
            int fatorDir = calcularFatorDeBalanceamento(this.dir);
            System.out.println("Fator de balanceamento do nó " + this.dir.x + ": " + fatorDir);
        }
        atualizarAltura(this);
       
    }
  
   public void atualizarAltura(Bnode no) {
        int alturaEsq = 0, alturaDir = 0;
        if (no.esq != null) alturaEsq = no.esq.altura;
        if (no.dir != null) alturaDir = no.dir.altura;
        no.altura = Math.max(alturaEsq, alturaDir) + 1;
    }
    public Bnode balancear(Bnode no) {
        
    
        
        if (fatorBalanceamento == 2) {
            if(calcularFatorDeBalanceamento(no.esq) == 0){
        
         
            
            System.out.println("Rotação à esquerda em " + no.x + " ...");
            return rotacaoEsquerda(no); 
            }else if(calcularFatorDeBalanceamento(no.esq)!=0){
                return rotacaoDuplaDireita(no);
            }else {
                return rotacaoDuplaEsquerda(no);
            }
        }

        if (fatorBalanceamento == -2) {
        if(calcularFatorDeBalanceamento(no.dir) == 0){
        
            System.out.println("Rotação à direita em " + no.x + " ...");
            return rotacaoDireita(no); 
        }else if(calcularFatorDeBalanceamento(no.dir)!=0){
            return rotacaoDuplaDireita(no);
        }else {
            return rotacaoDuplaEsquerda(no);
        }
        }
    
        return no; // A árvore já está balanceada
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
            return alturaDir - alturaEsq;
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
