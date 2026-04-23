package org.example.Classes;

import org.example.Service.AdocaoService;
import org.example.Service.AnimalService;

import java.util.Scanner;

public class Gerenciamento {

    private final Scanner scanner = new Scanner(System.in);
    private final AnimalService animalService;
    private final AdocaoService adocaoService;

    public Gerenciamento() {
        GestaoAdocao gestaoAdocao = GestaoAdocao.getInstance();
        gestaoAdocao.addObserver(message -> System.out.println("Notifica\u00E7\u00E3o: " + message));
        this.animalService = new AnimalService(gestaoAdocao);
        this.adocaoService = new AdocaoService(gestaoAdocao);
    }

    public void iniciar() {
        boolean quit = false;
        while (!quit) {
            printMenu();
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Op\u00E7\u00E3o inv\u00E1lida, tente novamente.");
                continue;
            }
            int action;
            try {
                action = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Op\u00E7\u00E3o inv\u00E1lida, tente novamente.");
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
                    System.out.println("Op\u00E7\u00E3o inv\u00E1lida, tente novamente.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Adicionar Animal");
        System.out.println("2 - Realizar Ado\u00E7\u00E3o");
        System.out.println("3 - Deletar Animal");
        System.out.println("4 - Vacinar Animal");
        System.out.println("5 - Listar Animais");
        System.out.println("6 - Sair");
        System.out.print("Escolha uma op\u00E7\u00E3o: ");
    }

    private void addAnimal() {
        System.out.print("Digite o ID do animal (apenas n\u00FAmeros): ");
        String id = scanner.nextLine();
        System.out.print("Digite a esp\u00E9cie do animal (c\u00E3o ou gato): ");
        String especie = scanner.nextLine();
        System.out.print("Digite a ra\u00E7a do animal: ");
        String raca = scanner.nextLine();
        System.out.print("Digite a idade do animal (apenas n\u00FAmeros): ");

        try {
            int idade = Integer.parseInt(scanner.nextLine());
            animalService.adicionarAnimal(id, especie, raca, idade);
        } catch (NumberFormatException e) {
            System.out.println("Idade inv\u00E1lida, deve conter apenas n\u00FAmeros.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void adoptAnimal() {
        System.out.print("Digite o ID do adotante: ");
        String adotanteId = scanner.nextLine();
        System.out.print("Digite o nome do adotante: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endere\u00E7o do adotante: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite a esp\u00E9cie preferida para ado\u00E7\u00E3o (c\u00E3o ou gato): ");
        String especiePreferida = scanner.nextLine();

        try {
            String animalAdotado = adocaoService.realizarAdocao(adotanteId, nome, endereco, especiePreferida);
            if (animalAdotado != null) {
                System.out.println("Ado\u00E7\u00E3o realizada com sucesso para o animal: " + animalAdotado);
            } else {
                System.out.println("N\u00E3o h\u00E1 animais dispon\u00EDveis que correspondam \u00E0 esp\u00E9cie preferida.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteAnimal() {
        System.out.print("Digite o ID do animal a ser deletado: ");
        String animalId = scanner.nextLine();
        if (animalService.deletarAnimal(animalId)) {
            System.out.println("Animal deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar o animal, pode estar vinculado a um adotante.");
        }
    }

    private void vaccinateAnimal() {
        System.out.print("Digite o ID do animal a ser vacinado: ");
        String animalId = scanner.nextLine();
        if (animalService.vacinarAnimal(animalId)) {
            System.out.println("Animal vacinado com sucesso.");
        } else {
            System.out.println("Animal n\u00E3o encontrado.");
        }
    }

    private void listAnimais() {
        System.out.println("\nLista de Animais Dispon\u00EDveis:");
        for (String descricaoAnimal : animalService.listarAnimaisDisponiveis()) {
            System.out.println(descricaoAnimal);
        }
    }
}
