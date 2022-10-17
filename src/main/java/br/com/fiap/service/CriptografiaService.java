package br.com.fiap.service;

public class CriptografiaService {
    
    public String criptografar(String senha){
        var senhaCriptografada = "";

        for(int i = 0; i < senha.length(); i++){
            senhaCriptografada += (char) (senha.charAt(i) + 1);
        }

        return senhaCriptografada;
    }

    public String descriptografar(String senhaCriptografada){
        var senha = "";

        for(int i = 0; i < senhaCriptografada.length(); i++){
            senha += (char) (senhaCriptografada.charAt(i) - 1);
        }

        return senha;
    }
    

}
