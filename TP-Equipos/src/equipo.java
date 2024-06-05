import java.util.LinkedList;
import javax.swing.JOptionPane;

public class equipo {

	private String nombre;
	private String ciudad;
	private LinkedList<jugador> jugadores = new LinkedList<jugador>();

	public equipo(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public LinkedList<jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(LinkedList<jugador> jugadores) {
		this.jugadores = jugadores;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Equipo: ").append(nombre).append("\n");
		sb.append("Jugadores:\n");
		for (jugador jugador : jugadores) {
			sb.append(" - ").append(jugador).append("\n");
		}
		return sb.toString();
	}

	public void cargarboca(equipo equipo1) {
		equipo1.getJugadores().add(new jugador("Romero", "Arquero", 37, 1));
		equipo1.getJugadores().add(new jugador("Lema", "Defensor", 23, 2));
		equipo1.getJugadores().add(new jugador("Rojo", "Defensor", 34, 6));
		equipo1.getJugadores().add(new jugador("Advincula", "Defensor", 34, 17));
		equipo1.getJugadores().add(new jugador("Blanco", "Defensor", 27, 23));
		equipo1.getJugadores().add(new jugador("Equi Fernandez", "Mediocampista", 21, 21));
		equipo1.getJugadores().add(new jugador("Fernandez", "Mediocampista", 32, 8));
		equipo1.getJugadores().add(new jugador("Medina", "Mediocampista", 22, 36));
		equipo1.getJugadores().add(new jugador("Zenon", "Mediocampista", 22, 22));
		equipo1.getJugadores().add(new jugador("Cavani", "Delantero", 37, 10));
		equipo1.getJugadores().add(new jugador("Merentiel", "Delantero", 28, 16));
	}

	public void cargarriver(equipo equipo2) {
		equipo2.getJugadores().add(new jugador("Armani", "Arquero", 37, 1));
		equipo2.getJugadores().add(new jugador("Diaz", "Defensor", 28, 13));
		equipo2.getJugadores().add(new jugador("Diaz", "Defensor", 29, 17));
		equipo2.getJugadores().add(new jugador("Boselli", "Defensor", 20, 2));
		equipo2.getJugadores().add(new jugador("Sant'Anna", "Defensor", 26, 27));
		equipo2.getJugadores().add(new jugador("Fonseca", "Mediocampista", 25, 4));
		equipo2.getJugadores().add(new jugador("Kranevitter", "Mediocampista", 31, 5));
		equipo2.getJugadores().add(new jugador("Colidio", "Delantero", 24, 11));
		equipo2.getJugadores().add(new jugador("Echeverri", "Mediocampista", 18, 19));
		equipo2.getJugadores().add(new jugador("Solari", "Delantero", 23, 36));
		equipo2.getJugadores().add(new jugador("Borja", "Delantero", 31, 9));
	}

	//  ---------------------------------------  Agregar Jugador  ---------------------------------------  //
	public void agregarManualmente() {
		String nombre = JOptionPane.showInputDialog("Ingrese nombre del jugador");
		String posicion = JOptionPane.showInputDialog("Ingrese posicion del jugador");
		int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad del jugador"));
	  	int numcamiseta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese numero de jugador"));
		// ----------------------Verifica Edad----------------------//
		if (edad <= 18) {
			JOptionPane.showMessageDialog(null, "El jugador debe tener 18 años o más");
			return;
		}

		jugador nuevo = new jugador(nombre, posicion, edad, numcamiseta);
		boolean flag = true;
		for (jugador jugador : this.getJugadores()) {
			// ----------------------Verifica Num Camiseta----------------------//
			if (jugador.getNumcamiseta() == nuevo.getNumcamiseta()) {
				JOptionPane.showMessageDialog(null, "Este número ya existe");
				flag = false;
				break;
			}
		}
		if (flag) {
			this.getJugadores().add(nuevo);
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo agregar el jugador, el número ya existe");
		}
	}

	//  ---------------------------------------  Eliminar Jugador  ---------------------------------------  //
	public void eliminarjugador(String nombreJugador) {
		jugadores.removeIf(jugador -> jugador.getNombre().equals(nombreJugador));
	}

//  ---------------------------------------  Buscar Jugador  ---------------------------------------  //
	public String buscarJugador(String nombreJugador) {
		for (jugador jugador : jugadores) {
			if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
				return "Jugador encontrado: " + jugador;
			}
		}
		return "Jugador no encontrado.";
	}
}
