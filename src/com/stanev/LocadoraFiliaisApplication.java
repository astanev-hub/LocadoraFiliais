package com.stanev;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.stanev.domain.*;

public class LocadoraFiliaisApplication {
	
	public static Stack<String> logEmprestimos1 = new Stack<>();
	public static Stack<String> logEmprestimos2 = new Stack<>();

    public static void main(String[] args) {
    	
    	Carro[] frota1 = new Carro[5];
    	Carro[] frota2 = new Carro[4];

        //Carro modeloCarro = new Carro("ABC-1234", "Toyota", "SW4", "Cinza", 250, true);

        frota1[0] = new Carro("ABC-000" + 0, "Toyota", "Cinza Chumbo", "SW" + 0, 100, true);
        frota1[1] = new Carro("ABC-000" + 1, "Toyota", "Cinza Chumbo", "SW" + 1, 200, true);
        frota1[2] = new Carro("ABC-000" + 2, "Toyota", "Cinza Chumbo", "SW" + 2, 300, true);
        frota1[3] = new Carro("ABC-000" + 3, "Toyota", "Cinza Chumbo", "SW" + 3, 400, true);
        frota1[4] = new Carro("ABC-000" + 4, "Toyota", "Cinza Chumbo", "SW" + 4, 500, true);

        frota2[0] = new Carro("DEF-000" + 0, "Fiat", "Azul", "Toro" + 0, 10, true);
        frota2[1] = new Carro("DEF-000" + 1, "Fiat", "Azul", "Toro" + 1, 20, true);
        frota2[2] = new Carro("DEF-000" + 2, "Fiat", "Azul", "Toro" + 2, 30, true);
        frota2[3] = new Carro("DEF-000" + 3, "Fiat", "Azul", "Toro" + 3, 40, true);
        
        frota1 = ordenarVeiculos(frota1);
        frota2 = ordenarVeiculos(frota2);
        
        System.out.println("IMPRESSÃO DA FROTA 1 DE VEÍCULOS");
        System.out.println(Arrays.toString(frota1));

        System.out.println("IMPRESSÃO DA FROTA 2 DE VEÍCULOS");
        System.out.println(Arrays.toString(frota2));
            

        Queue<Cliente> filaEspera1 = new ArrayDeque<>();
        Queue<Cliente> filaEspera2 = new ArrayDeque<>();
        
        Cliente c1 = new Cliente("Alexandre", "11111111111", "Rua do Alexandre", frota1[0]);
        Cliente c2 = new Cliente("Armando", "11222222222", "Rua do Armando", frota1[2]);
        Cliente c3 = new Cliente("Amarildo", "11333333333", "Rua do Amarildo", frota1[4]);
        filaEspera1.add(c1);
        filaEspera1.add(c2);
        filaEspera1.add(c3);
        
        Cliente c4 = new Cliente("Bruno", "21111111111", "Rua do Bruno", frota2[1]);
        Cliente c5 = new Cliente("Benicio", "21222222222", "Rua do Benicio", frota2[2]);
        Cliente c6 = new Cliente("Bento", "21333333333", "Rua do Bento", frota2[3]);      
        filaEspera2.add(c4);
        filaEspera2.add(c5);
        filaEspera2.add(c6);
        
        //Emprestar carro para os clientes da filial 1
        int frotaAtual = 0;
        for (Cliente cliente : filaEspera1) {
            System.out.println("Empréstimo para o cliente: " + cliente.getNome());
            emprestarCarro(cliente, frota1[frotaAtual], logEmprestimos1);
            frotaAtual++;
            if (frotaAtual == 4) frotaAtual = 0;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Limpar lista de espera da filial 1, uma vez que todos já estão com carros
        filaEspera1.clear();
        
        //Emprestar carro para os clientes da filial 2
        frotaAtual = 0;
        for (Cliente cliente : filaEspera2) {
        	System.out.println("Empréstimo para o cliente: " + cliente.getNome());
        	emprestarCarro(cliente, frota2[frotaAtual], logEmprestimos2);
            frotaAtual++;
            if (frotaAtual == 3) frotaAtual = 0;
        	try {
        		Thread.sleep(1000);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        }
        //Limpar lista de espera da filial 2, uma vez que todos já estão com carros
        filaEspera2.clear();

        //Simular duas devoluções de carros, uma em cada filial
        devolverCarro(c1, frota1[0], logEmprestimos1); 
        devolverCarro(c4, frota2[0], logEmprestimos2); 
        

        //Simular a inclusão dos dois clientes que devolveram os carros na lista de espera de suas filias de novo
        filaEspera1.add(c1);
        filaEspera2.add(c4);

  
        Filial filial1 = new Filial(1, logEmprestimos1, frota1, filaEspera1);
        Filial filial2 = new Filial(2, logEmprestimos2, frota2, filaEspera2);
       
        LinkedList<Filial> filiais = new LinkedList<>();
       
        filiais.add(filial1);
        filiais.add(filial2);
        
        listaCompletaDasFiliais(filiais);    
        listaDeUmaFilialEspecifica(filiais, 1);
        quantidadeDeCarrosPorFilial(filiais, 2);    
        quantidadeTotalDeCarrosDasFiliais(filiais);
   		quantidadeTotalDeCarrosAlugadosDasFiliais(filiais); 		
   		quantidadeDeClientesNaListaDeEspera(filiais); 		
   		totalDasDiariasDosCarrosAlugados(filiais);
    }

    public static void imprimirFilaEspera(Queue<Cliente> fila){
        for (Cliente cliente : fila) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Modelo: " + cliente.getCarroFavorito().getModelo());
        }
    }

    public static Carro[] ordenarVeiculos(Carro[] veiculos) {
        Arrays.sort(veiculos);

        return veiculos;
    }

    public static void emprestarCarro(Cliente cliente, Carro carro, Stack<String> logEmprestimos) {
        carro.setDisponivel(false);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy à's' HH:mm:ss"); //formatar a data como 12/04/2022 às 16:37:15

        logEmprestimos.push(dateFormat.format(Calendar.getInstance().getTime()) + ": Carro " + carro.getMarca() + ", " + carro.getModelo() + ", " + carro.getPlaca() + ", foi EMPRESTADO para " + cliente.getNome());
    }

    public static void devolverCarro(Cliente cliente, Carro carro, Stack<String> logEmprestimos) {
        carro.setDisponivel(true);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy à's' HH:mm:ss"); //formatar a data como 12/04/2022 às 16:37:15

        logEmprestimos.push(dateFormat.format(Calendar.getInstance().getTime()) + ": Carro " + carro.getMarca() + ", " + carro.getModelo() + ", " + carro.getPlaca() + ", foi DEVOLVIDO por " + cliente.getNome());
    }
    
    public static void listaCompletaDasFiliais(LinkedList<Filial> filiais) {
        System.out.println("LISTA COMPLETA COM TUDO, DE TODAS AS FILIAS");
        System.out.println(filiais.toString());    	
    }

    public static void listaDeUmaFilialEspecifica(LinkedList<Filial> filiais,int numeroFilial) {
        System.out.println("\nLISTA DO LOG DA FILIAL 1");
        for (Filial f: filiais) {
        	if (f.filial == numeroFilial) {
        		System.out.println(f.logEmprestimos);
        	}
        }
    }

    public static void quantidadeDeCarrosPorFilial(LinkedList<Filial> filiais,int numeroFilial) {
        System.out.println("\nQUANTIDADE TOTAL DE CARROS DA FILIAL 1");
        for (Filial f: filiais) {
        	if (f.filial == numeroFilial) {
        		System.out.println(f.getFrota().length);
        	}
        }    
    	
    }

    public static void quantidadeTotalDeCarrosDasFiliais(LinkedList<Filial> filiais) {
        System.out.println("\nQUANTIDADE TOTAL DE CARROS DE TODAS AS FILIAIS");
        int totalDeCarros = 0;
        for (Filial f: filiais) {
        	totalDeCarros+= f.getFrota().length;
        }    
   		System.out.println(totalDeCarros);    	
    }

	public static void	quantidadeTotalDeCarrosAlugadosDasFiliais(LinkedList<Filial> filiais) {
		System.out.println("\nQUANTIDADE DE CARROS ALUGADOS ATUALMENTE");
		int totalDeCarrosAlugados = 0;
		for (Filial f: filiais) {
			for (Carro c: f.getFrota()) {
				if (!c.isDisponivel()) totalDeCarrosAlugados++;
			}
		}    
		System.out.println(totalDeCarrosAlugados);	
	}

	public static void quantidadeDeClientesNaListaDeEspera(LinkedList<Filial> filiais) {
		System.out.println("\nQUANTIDADE DE CLIENTES NA LISTA DE ESPERA");
		int totalDeClienteEmEspera = 0;
		for (Filial f: filiais) {
			totalDeClienteEmEspera+= f.getFilaEspera().size();
		}    
		System.out.println(totalDeClienteEmEspera);		
	}

	public static void totalDasDiariasDosCarrosAlugados(LinkedList<Filial> filiais) {
		System.out.println("\nTOTAL (EM DIARIAS) DOS CARROS ALUGADOS");
		int totalDasDiarias = 0;
		for (Filial f: filiais) {
			for (Carro c: f.getFrota()) {
				if (!c.isDisponivel()) totalDasDiarias+= c.getValorDiaria();
			}
		}    
		System.out.println(totalDasDiarias);		
	}

}