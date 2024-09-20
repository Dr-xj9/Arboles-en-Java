import java.util.*;

public class Main {
    static class Clase {
        String profesor;
        String salon;
        int horario;

        Clase(String profesor, String salon, int horario) {
            this.profesor = profesor;
            this.salon = salon;
            this.horario = horario;
        }

        @Override
        public String toString() {
            return "Profesor: " + profesor + ", Salón: " + salon + ", Horario: " + horario;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar el número de profesores y salones
        System.out.print("Ingrese el número de profesores: ");
        int numProfesores = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese el número de salones: ");
        int numSalones = scanner.nextInt();
        
        // Solicitar el rango de horarios
        System.out.print("Ingrese el rango de horarios (por ejemplo, 8 para 8-9 AM): ");
        int rangoHorario = scanner.nextInt();
        
		System.out.print("Ingrese el rango de duracion: ");
		int horasClase = scanner.nextInt(); // Definir cuántas horas hay en el rango (por ejemplo, 8 a 12 son 5 horas)

        // Lista de profesores y salones
        List<String> profesores = new ArrayList<>();
        List<String> salones = new ArrayList<>();
        
        for (int i = 0; i < numProfesores; i++) {
            System.out.print("Ingrese el nombre del profesor " + (i + 1) + ": ");
            profesores.add(scanner.nextLine());
        }
        
        for (int i = 0; i < numSalones; i++) {
            System.out.print("Ingrese el nombre del salón " + (i + 1) + ": ");
            salones.add(scanner.nextLine());
        }

        // Generar horarios
        List<Clase> horarios = generarHorarios(profesores, salones, horasClase);

        // Mostrar los horarios generados
        System.out.println("\nHorarios generados:");
        for (Clase clase : horarios) {
            System.out.println(clase);
        }
    }

    private static List<Clase> generarHorarios(List<String> profesores, List<String> salones, int horasClase) {
        List<Clase> clases = new ArrayList<>();
        Random random = new Random();

        // Generar horarios aleatorios
        Set<String> ocupaciones = new HashSet<>(); // Para evitar conflictos

        for (String profesor : profesores) {
            for (int h = 0; h < horasClase; h++) {
                int horario = h + 1; // Horarios de 1 a horasClase
                String salon = salones.get(random.nextInt(salones.size()));
                String ocupacion = profesor + "-" + salon + "-" + horario;

                // Asegurarse de que no hay conflictos
                while (ocupaciones.contains(ocupacion)) {
                    salon = salones.get(random.nextInt(salones.size()));
                    ocupacion = profesor + "-" + salon + "-" + horario;
                }

                clases.add(new Clase(profesor, salon, horario));
                ocupaciones.add(ocupacion);
            }
        }

        return clases;
    }
}
