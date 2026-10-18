package com.unicoast.project.builder.solution;

/*
 * PRODUCTO COMPLEJO (GameCharacter)
 *
 * El objeto solo expone sus atributos finales y se construye exclusivamente
 * a través de su clase interna 'Builder'.
 */
public class GameCharacter {
    private String name;
    private String type;

    // Constructor que recibe el Builder con todos los datos ya configurados
    public GameCharacter(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
    }

    public void showStats() {
        System.out.println("Personaje: " + (name != null ? name : "Sin nombre"));
        System.out.println("Clase: " + (type != null ? type : "Clase no asignada"));
    }

    /*
     * CLASE CONSTRUCTORA (BUILDER ESTÁTICO)
     *
     * Es 'static' para poder instanciarla directamente con 'new GameCharacter.Builder()'
     * sin requerir una instancia previa de 'GameCharacter'.
     */
    public static class Builder {
        private String name;
        private String type;

        // Métodos encadenados: devuelven 'this' para encadenar llamadas sucesivas
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        // Método final que construye y entrega el objeto listo
        public GameCharacter build() {
            return new GameCharacter(this);
        }
    }
}
