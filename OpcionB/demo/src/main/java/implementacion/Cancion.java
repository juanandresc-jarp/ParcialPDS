package implementacion;

public class Cancion {
    private Long id;
    private String NombreCancion;
    private Artista artista;
    private byte[] Cancion = new byte[1000000];
    
    public Cancion(Long id, String NombreCancion, Artista artista) {
        this.id = id;
        this.NombreCancion = NombreCancion;
        this.artista = artista;
    }
    

    public Cancion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Artista getArtista() {
        return artista;
    }
    
    public void setArtista(Artista artista) {
        this.artista = artista;
    }    
    public String getNombreCancion() { return NombreCancion; }
    public void setNombreCancion(String NombreCancion) { this.NombreCancion = NombreCancion; }

    @Override
    public String toString() {
        return "Canción{" + "id=" + id + ", Tema=" + NombreCancion + ", Artista=" + artista.getNombre() + '}';
    }

}
