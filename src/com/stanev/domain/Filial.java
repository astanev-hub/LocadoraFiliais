package com.stanev.domain;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class Filial {
	public int filial;
	public Stack<String> logEmprestimos = new Stack<>();
	Carro[] frota = new Carro[5];
	Queue<Cliente> filaEspera = new ArrayDeque<>();
	
	public Filial(int filial, Stack<String> logEmprestimos, Carro[] frota, Queue<Cliente> filaEspera) {
		this.filial = filial;
		this.logEmprestimos = logEmprestimos;
		this.frota = frota;
		this.filaEspera = filaEspera;
	}

	public Stack<String> getLogEmprestimos() {
		return logEmprestimos;
	}

	public void setLogEmprestimos(Stack<String> logEmprestimos) {
		this.logEmprestimos = logEmprestimos;
	}

	public Carro[] getFrota() {
		return frota;
	}

	public void setFrota(Carro[] frota) {
		this.frota = frota;
	}

	public Queue<Cliente> getFilaEspera() {
		return filaEspera;
	}

	public void setFilaEspera(Queue<Cliente> filaEspera) {
		this.filaEspera = filaEspera;
	}

	@Override
	public String toString() {
		return "Filial [filial=" + filial + ", logEmprestimos=" + logEmprestimos + ", frota=" + Arrays.toString(frota)
				+ ", filaEspera=" + filaEspera + "]";
	}

}
