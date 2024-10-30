public class Btree {
    // atributos da classe
    Bnode raiz;

    // construtor
    public Btree(){
        raiz = null;
    }

    public void add(int valor){
        if(raiz!=null){
            raiz.add(valor);
        }
        else{
            raiz = new Bnode(valor);
        }
    }

    public void show(){
        if(raiz!=null) raiz.show();
        else System.out.println("Arvore Vazia!!! ");
    }

    public int size(){
        if(raiz!=null) return raiz.size();
        return 0;
    }

    public int calcularAltura(Bnode node){
         if(raiz!=null) return raiz.calcularAltura(node);
         return 0;
          
    }
   
    public int rotacaoDireita(Bnode node){
           if(raiz!=null)  raiz.rotacaoDireita(node);
           return 0;
    }
    public int soma(){
        if(raiz!=null) return raiz.soma();
        return 0;
    }

  
    
}
