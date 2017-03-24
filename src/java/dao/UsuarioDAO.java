/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author marcelosiedler
 */
public class UsuarioDAO {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    public UsuarioDAO() {

    }

    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO tbl_usuarios(nome,sobrenome,email,senha,data_nascimento,data_inscricao,sexo,pais,estado,cidade) VALUES (?,?,?,md5(?),?,now(),?,?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getSobrenome());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getSenha());
            pst.setString(5, usuario.getNascimento());
            pst.setString(6, usuario.getSexo());
            pst.setString(7, usuario.getPais());
            pst.setString(8, usuario.getEstado());
            pst.setString(9, usuario.getCidade());
            if (pst.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;

    }

    public boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuario set senha=?,perfil=?,email=? where login=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {

            pst.setString(1, usuario.getSenha());
            //pst.setString(2, usuario.getPerfil());
            pst.setString(3, usuario.getEmail());
            //pst.setString(4, usuario.getLogin());
            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }

        return retorno;

    }

    public boolean excluir(Usuario usuario) {
        String sql = "DELETE FROM tbl_usuarios where id_usuario=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            System.out.println(usuario.getNome());
            pst.setString(1, usuario.getNome());
            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;

    }

    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> retorno = new ArrayList<Usuario>();

        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {

            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Usuario item = new Usuario();
                //item.setLogin(res.getString("login"));
                item.setSenha(res.getString("senha"));
                item.setEmail(res.getString("email"));
                //item.setPerfil(res.getString("perfil"));

                retorno.add(item);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return retorno;

    }

    public Usuario buscar(Usuario usuario) {
        //String sql = "SELECT * FROM tbl_usuarios where email=? and senha=?";
        String sql = "SELECT * FROM tbl_usuarios where nome=?";
        Usuario retorno = null;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1, usuario.getNome());
            //pst.setString(1, usuario.getEmail());
            //pst.setString(2, usuario.getSenha());
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                retorno = new Usuario();
                retorno.setSenha(res.getString("senha"));
                retorno.setEmail(res.getString("email"));
                retorno.setNome(res.getString("nome"));
            } else {
                System.out.println("usuario não encontrado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Usuario login(Usuario usuario) {
        String sql = "SELECT * FROM tbl_usuarios where email=? and senha=md5(?)";
        Usuario retorno = null;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                retorno = new Usuario();
                retorno.setNome(res.getString("nome"));
                retorno.setSobrenome(res.getString("sobrenome"));
                retorno.setCidade(res.getString("cidade"));
            } else {
                System.out.println("usuario não encontrado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
