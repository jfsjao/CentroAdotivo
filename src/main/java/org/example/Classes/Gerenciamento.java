package org.example.Classes;

import org.example.Factory.AdotanteFactory;
import org.example.Factory.AnimalFactory;
import org.example.Decorator.VaccinatedDecorator;
import org.example.Interface.IAnimal;

import java.util.Scanner;

public class Gerenciamento {

    private final Scanner scanner = new Scanner(System.in);
    private final GestaoAdocao gestaoAdocao;

    public Gerenciamento() {
        this.gestaoAdocao = GestaoAdocao.getInstance();
        this.gestaoAdocao.addObserver(message -> System.out.println("Notificação: " + message));
    }

    public void iniciar() {
        boolean quit = false;
        while (!quit) {
            printMenu();
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Opção inválida, tente novamente.");
                continue;
            }
            int action;
            try {
                action = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida, tente novamente.");
                continue;
            }
            switch (action) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    adoptAnimal();
                    break;
                case 3:
                    deleteAnimal();
                    break;
                case 4:
                    vaccinateAnimal();
                    break;
                case 5:
                    listAnimais();
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Adicionar Animal");
        System.out.println("2 - Realizar Adoção");
        System.out.println("3 - Deletar Animal");
        System.out.println("4 - Vacinar Animal");
        System.out.println("5 - Listar Animais");
        System.out.println("6 - Sair");
        System.out.print("Escolha uma opção: ");
    }


    private void addAnimal() {
        System.out.print("Digite o ID do animal (apenas números): ");
        String id = scanner.nextLine();
        if (!id.matches("\\d+")) {
            System.out.println("ID inválido, deve conter apenas números.");
            return;
        }

        System.out.print("Digite a espécie do animal (cão ou gato): ");
        String especie = scanner.nextLine();
        if (!especie.equalsIgnoreCase("cão") && !especie.equalsIgnoreCase("gato")) {
            System.out.println("Espécie inválida, deve ser 'cão' ou 'gato'.");
            return;
        }

        System.out.print("Digite a raça do animal: ");
        String raca = scanner.nextLine();
        System.out.print("Digite a idade do animal (apenas números): ");
        int idade;
        try {
            idade = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Idade inválida, deve conter apenas números.");
            return;
        }

        Animal animal = AnimalFactory.criarAnimal(id, especie, raca, idade);
        gestaoAdocao.adicionarAnimal(animal);
    }

    private void adoptAnimal() {
        System.out.print("Digite o ID do adotante: ");
        String adotanteId = scanner.nextLine();
        System.out.print("Digite o nome do adotante: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço do adotante: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite a espécie preferida para adoção (cão ou gato): ");
        String especiePreferida = scanner.nextLine();
        if (!especiePreferida.equalsIgnoreCase("cão") && !especiePreferida.equalsIgnoreCase("gato")) {
            System.out.println("Espécie inválida, deve ser 'cão' ou 'gato'.");
            return;
        }

        Adotante adotante = AdotanteFactory.criarAdotante(adotanteId, nome, endereco, especiePreferida);
        gestaoAdocao.registrarAdotante(adotante);

        boolean matchFound = false;
        for (IAnimal animal : gestaoAdocao.getAnimaisDisponiveis()) {
            if (animal.getEspecie().equalsIgnoreCase(especiePreferida)) {
                if (gestaoAdocao.realizarAdocao(animal, adotante, (a, b) -> a.getEspecie().equals(b.getPreferenciaEspecie()))) {
                    System.out.println("Adoção realizada com sucesso para o animal: " + animal.getDescricao());
                    matchFound = true;
                    break;
                }
            }
        }

        if (!matchFound) {
            System.out.println("Não há animais disponíveis que correspondam à espécie preferida.");
        }
    }

    private void deleteAnimal() {
        System.out.print("Digite o ID do animal a ser deletado: ");
        String animalId = scanner.nextLine();
        if (gestaoAdocao.deletarAnimal(animalId)) {
            System.out.println("Animal deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar o animal, pode estar vinculado a um adotante.");
        }
    }

    private void vaccinateAnimal() {
        System.out.print("Digite o ID do animal a ser vacinado: ");
        String animalId = scanner.nextLine();
        IAnimal animal = gestaoAdocao.buscarAnimal(animalId);
        if (animal != null) {
            // Criando o decorador e atualizando o objeto IAnimal diretamente
            IAnimal decoratedAnimal = new VaccinatedDecorator(animal);
            // Atualizando a lista de animais disponíveis com o objeto decorado
            gestaoAdocao.getAnimaisDisponiveis().remove(animal);
            gestaoAdocao.getAnimaisDisponiveis().add(decoratedAnimal);
            System.out.println("Animal vacinado com sucesso.");
        } else {
            System.out.println("Animal não encontrado.");
        }
    }

    private void listAnimais() {
        System.out.println("\nLista de Animais Disponíveis:");
        for (IAnimal animal : gestaoAdocao.getAnimaisDisponiveis()) {
            System.out.println(animal.getDescricao());
        }
    }
}
