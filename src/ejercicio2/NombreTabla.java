package ejercicio2;

public enum NombreTabla {
    HORARIO("horario"),OFERTA_EDUCATIVA("ofertaeducativa"),PROFESOR("profesor"),REPARTO("reparto"),TRAMO_HORARIO("tramohorario"),CURSO("curso"),ASIGNATURA("asignatura");
    private String valor;
    private NombreTabla(String valor){
        this.valor=valor;
    }
    public String getValor(){
        return valor;
    }
}
