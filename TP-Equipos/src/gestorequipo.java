import java.util.LinkedList;
import javax.swing.JOptionPane;

public class gestorequipo {

	private LinkedList<equipo> equipos = new LinkedList<equipo>();

	@Override
	public String toString() {
		 StringBuilder lista = new StringBuilder("Equipos:\n");
		    for (equipo equipo : equipos) {
		        lista.append(equipo).append("\n");
		    }
		    return lista.toString();
	}

	public LinkedList<equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(LinkedList<equipo> equipos) {
		this.equipos = equipos;
	}

	// --------------------------------------- Menu
	// --------------------------------------- //

	public void menuequipos(equipo equipo1, equipo equipo2) {
		equipo1.cargarboca(equipo1);
		equipo2.cargarriver(equipo2);

		String[] equipos = { equipo1.getNombre(), equipo2.getNombre(), "Ajustes", "Salir" };
		int equi;
		do {
			equi = JOptionPane.showOptionDialog(null, "Seleccione un equipo", "Equipos", 0, 0, null, equipos,
					equipos[0]);

			switch (equi) {
			case 0:
				String[] accionar = { "Agregar Jugador", "Ver Lista", "Eliminar Jugador", "Buscar Un Jugador",
						"Volver Al Menu" };
				int accion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Equipos", 0, 0, null,
						accionar, accionar[0]);

				if (accion == 0) {
					equipo1.agregarManualmente();
					JOptionPane.showMessageDialog(null, equipo1);
				} else if (accion == 1) {
					JOptionPane.showMessageDialog(null, equipo1);
				} else if (accion == 2) {
					eliminarJugador(equipo1);
				} else if (accion == 3) {
					String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador a buscar:");
					String resultadoBusqueda = equipo1.buscarJugador(nombreJugador);
					JOptionPane.showMessageDialog(null, resultadoBusqueda);
				}
				break;
			case 1:
				String[] accionar2 = { "Agregar Jugador", "Ver Lista", "Eliminar Jugador", "Buscar Un Jugador",
						"Volver Al Menu" };
				int accion2 = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Equipos", 0, 0, null,
						accionar2, accionar2[0]);

				if (accion2 == 0) {
					equipo2.agregarManualmente();
					JOptionPane.showMessageDialog(null, equipo2);
				} else if (accion2 == 1) {
					JOptionPane.showMessageDialog(null, equipo2);
				} else if (accion2 == 2) {
					eliminarJugador(equipo2);
				} else if (accion2 == 3) {
					String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador a buscar:");
					String resultadoBusqueda = equipo2.buscarJugador(nombreJugador);
					JOptionPane.showMessageDialog(null, resultadoBusqueda);
				}
				break;
			case 2:
				Ajustes();
				return;
			case 3:

				return;

			default:
				break;
			}

		} while (equi != 3);
	}

	// --------------------------------------- Funcion Eliminar Un Jugador
	// --------------------------------------- //

	private void eliminarJugador(equipo equipo) {
		if (equipo.getJugadores().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay jugadores para eliminar");
			return;
		}

		String[] nombresJugadores = equipo.getJugadores().stream().map(jugador::getNombre).toArray(String[]::new);
		String jugadorAEliminar = (String) JOptionPane.showInputDialog(null, "Seleccione el jugador a eliminar",
				"Eliminar Jugador", JOptionPane.QUESTION_MESSAGE, null, nombresJugadores, nombresJugadores[0]);

		if (jugadorAEliminar != null) {
			equipo.eliminarjugador(jugadorAEliminar);
			JOptionPane.showMessageDialog(null, "Jugador eliminado: " + jugadorAEliminar);
		}
	}

	// --------------------------------------- Funcion Jugar Partido
	// --------------------------------------- //

	public String jugarpartido(equipo equipo1, equipo equipo2) {

		equipo1.cargarboca(equipo1);
		equipo2.cargarriver(equipo2);

		String[] opcionesEquipos = { equipo1.getNombre(), equipo2.getNombre() };
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione su equipo", "Jugar Partido", 0,
				JOptionPane.QUESTION_MESSAGE, null, opcionesEquipos, opcionesEquipos[0]);

		equipo local;
		equipo visitante;
		if (seleccion == 0) {
			local = equipo1;
			visitante = equipo2;
		} else {
			local = equipo2;
			visitante = equipo1;
		}
		if (equipo1.getJugadores().size() >= 8 && equipo2.getJugadores().size() >= 8) {
			int goles1 = (int) (Math.random() * 2);
			int goles2 = (int) (Math.random() * 2);

			if (goles1 == goles2) {
				JOptionPane.showMessageDialog(null, "Empataron: " + goles1 + "-" + goles2);

				// ----------------------Penales----------------------//
				int penalesEquipo1 = 0;
				int penalesEquipo2 = 0;

				for (int i = 0; i < 2; i++) {
					if (Math.random() > 0.5) {
						penalesEquipo1++;
					}
					if (Math.random() > 0.5) {
						penalesEquipo2++;
					}
				}
				JOptionPane.showMessageDialog(null, "Resultado penales: " + equipo1.getNombre() + " " + penalesEquipo1
						+ " - " + equipo2.getNombre() + " " + penalesEquipo2);
				while (penalesEquipo1 == penalesEquipo2) {
					if (Math.random() > 0.5) {
						penalesEquipo1++;
					} else {
						penalesEquipo2++;
					}
					JOptionPane.showMessageDialog(null, "Nueva ronda penales: " + equipo1.getNombre() + " "
							+ penalesEquipo1 + " - " + equipo2.getNombre() + " " + penalesEquipo2);
				}

				if (goles1 > goles2 || penalesEquipo1 > penalesEquipo2) {
					JOptionPane.showMessageDialog(null, "Ganó el equipo: " + equipo1.getNombre());
					return equipo1.getNombre() + " " + goles1 + '-' + goles2;
				} else {
					JOptionPane.showMessageDialog(null, "Ganó el equipo: " + equipo2.getNombre());
					return equipo2.getNombre() + " " + goles2 + '-' + goles1;
				}

			} else {
				// ----------------------Solo Goles----------------------//
				if (goles1 > goles2) {
					JOptionPane.showMessageDialog(null, "Ganó el equipo: " + equipo1.getNombre());
					return equipo1.getNombre() + " " + goles1 + '-' + goles2;
				} else {
					JOptionPane.showMessageDialog(null, "Ganó el equipo: " + equipo2.getNombre());
					return equipo2.getNombre() + " " + goles2 + '-' + goles1;
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "No hay suficientes jugadores");
		}
		return null;
	}

	// --------------------------------------- Menu Ajustes
	// --------------------------------------- //

	public void Ajustes() {
		
		getEquipos();
		
		String[] ajustes = { "Agregar Equipo", "Eliminar Equipo", "Buscar Equipo", "Ver Equipos", "Salir" };
		int ajus;
		do {
			ajus = JOptionPane.showOptionDialog(null, "Opciones", "Menu de Gestión de Equipos",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ajustes, ajustes[0]);

			switch (ajus) {
			case 0:
				agregarequipo();
				
				break;
			case 1:
				eliminarequipo(null);
				break;
			case 2:
				buscarequipo(null);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, equipos);
				break;
			case 4:
				// Opción para salir
				break;
			default:
				break;
			}
		} while (ajus != 4);

	}

//  ---------------------------------------  Eliminar Equipo  ---------------------------------------  //

	public void eliminarequipo(String nombreEquipo) {
		equipos.removeIf(equipo -> equipo.getNombre().equals(nombreEquipo));
	}

//  ---------------------------------------  Buscar Equipo  ---------------------------------------  //

	public String buscarequipo(String nombreEquipo) {
		for (equipo equipo : equipos) {
			if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
				return "Equipo encontrado: " + equipo;
			}
		}
		return "Equipo no encontrado.";
	}

	// --------------------------------------- Agregar Equipo
	// --------------------------------------- //

	 public void agregarequipo() {
		 
	        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo equipo:");
	        String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad del nuevo equipo:");
	         
	        
	        equipo nuevo = new equipo(nombre, ciudad);
	       
	        boolean flag = true;
	        for (equipo equipo : equipos) {
	            // ----------------------Verifica el nombre ----------------------//
	            if (equipo.getNombre() == nuevo.getNombre()) {
	                JOptionPane.showMessageDialog(null, "Este nombre de equipo ya existe");
	                flag = false;
	                break;
	            }
	        }
	        if (flag) {
	            equipos.add(nuevo);
	            JOptionPane.showMessageDialog(null, "Equipo agregado exitosamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo agregar el equipo, el nombre ya existe");
	        }
	    }

	// --------------------------------------- Contar Equipos
	// --------------------------------------- //
	public int contarEquipos(equipo equipo) {
		return equipos.size();
	}

	
}
