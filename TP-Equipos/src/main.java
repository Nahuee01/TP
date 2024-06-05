
import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		mostrarMenuPrincipal();
	}
	
	public static void mostrarMenuPrincipal() {
		equipo boca = new equipo("Boca", "La Boca");
		equipo river = new equipo("River", "Nuñez");

		String[] MenuPrin = { "Equipos", "Jugar", "Salir" };
		gestorequipo gestor = new gestorequipo();
		int opc;

		do {
			opc = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Menu Principal", 0, 0, null, MenuPrin,
					MenuPrin[0]);

			switch (opc) {
			case 0:
				gestor.menuequipos(boca, river);
				break;
			case 1:
				gestor.jugarpartido(boca, river);
				break;
			case 2: // Salir
				return;
			default:
				break;
			}
		} while (opc != 2);
	}
}