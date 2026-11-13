package br.ifms.edu.tsi.lpii;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class DataSourceJSON extends AbstractDataSource<Perguntas>{
	
    private Integer contador;
    private List<Perguntas> perguntas;
    private File arquivoContador;
    private File arquivoPerguntas;
    
    public DataSourceJSON() throws IOException {
        super();
        arquivoContador = new File(baseDir, "contador.json");
        arquivoPerguntas = new File(baseDir, "Perguntas.json");

        try {
            // Ler contador
            if (arquivoContador.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(arquivoContador));
                contador = gson.fromJson(reader, Integer.class);
                reader.close();
            } else {
                contador = 0;
                salvarContador();
            }

            // Ler lista de perguntas
            if (arquivoPerguntas.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(arquivoPerguntas));
                Type listType = new TypeToken<List<Perguntas>>() {}.getType();
                perguntas = gson.fromJson(reader, listType);
                reader.close();
            } else {
                perguntas = new ArrayList<>();
                salvarPerguntas();
            }
        } catch (IOException e) {
            throw new IOException("Erro ao inicializar DataSourceJSON: " + e.getMessage(), e);
        }
    }

    private void salvarContador() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoContador));
        gson.toJson(contador, writer);
        writer.close();
    }

    private void salvarPerguntas() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPerguntas));
        gson.toJson(perguntas, writer);
        writer.close();
    }

	@Override
	public void salvar(Perguntas t) throws Exception {
		// TODO Auto-generated method stub
        if (t.getId() == 0) {
            t.setId(++contador);
            salvarContador();
            perguntas.add(t);
        } else {
            for (Perguntas p : perguntas) {
                if (p.getId().equals(t.getId())) {
                    p.setCategoria(t.getCategoria());
                    p.setPergunta(t.getPergunta());
                    p.setOpcoes(t.getOpcoes());
                    p.setIdRespostaCorreta(t.getIdRespostaCorreta());
                    break;
                }
            }
        }
        salvarPerguntas();
		
	}

	@Override
	public void remover(Integer id) throws Exception {
		// TODO Auto-generated method stub
        perguntas.removeIf(p -> p.getId().equals(id));
        salvarPerguntas();
		
	}

	@Override
	public List<Perguntas> listar() throws Exception {
		// TODO Auto-generated method stub
		return perguntas;
	}
	
	public Set<String> listarCategorias() throws Exception {
		Set<String> categorias = new LinkedHashSet<>();
	    for (Perguntas p : perguntas) {
	        categorias.add(p.getCategoria());
	    }
	    return categorias;
	}
	
	public List<String> listarPerguntas() throws Exception {
	    List<String> pergunts = new ArrayList<>();
	    for (Perguntas p : perguntas) {
	        pergunts.add(p.getPergunta());
	    }
	    return pergunts;
	}

	@Override
	public Perguntas buscarPorId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		for (Perguntas p : perguntas) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
		return null;
	}

	public void salvar(List<Perguntas> perguntasList) throws Exception {
		for (Perguntas p : perguntasList) {
			salvar(p);
		}
		salvarPerguntas();
	}

}