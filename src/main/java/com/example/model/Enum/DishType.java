package com.example.model.Enum;

public enum DishType {
    STARTER("Entrantes"),
     MAIN("Plato principal"),
    DESSERT("Postre");
    private final String label;
    DishType(String label){
        this.label = label;
}
public String getLabel(){
        return label;
}

}
