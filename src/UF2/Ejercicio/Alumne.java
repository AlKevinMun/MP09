package Ejercicio;

public class Alumne {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public Alumne(String nombre) {
        this.nombre = nombre;
    }

    public int examinar(){
        return (int)(Math.random()*10+1);
    }
}
