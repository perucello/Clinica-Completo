package model;

public class PacienteDelete {
    
    private int id;
    private String nome;
    private float peso;
    private float altura;
    
        public PacienteDelete(){
        }
        
        public PacienteDelete(int id, String nome, float peso, float altura){
            this.id = id;
            this.nome = nome;
            this.peso = peso;
            this.altura = altura;
        }
       
        public int getId(){
            return id;
        }
        
        public void setId(int id){
            this.id = id;
        }
        
        public String getNome(){
            return nome;
        }
        
        public void setNome(String nome){
            this.nome = nome;
        }
        
        public float getPeso(){
            return peso;
        }
        
        public void setPeso(float peso){
            this.peso = peso;
        }
        
        public float getAltura(){
            return altura;
        }
        
        
        public void setAltura(float altura){
            this.altura = altura;
        }
        
        @Override
        public String toString(){
            return nome;
        }
}
