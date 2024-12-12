package br.com.erico.lavanderia.model.usuario;

import br.com.erico.lavanderia.model.acesso.AcessoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @NotBlank(message = "Informe o nome do usuário")
    private String nome;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail do usuário é obrigatório")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Informe a senha do usuário")
    @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "Informe o número de telefone do usuário")
    @Pattern(regexp = "(\\d){2}\\s(\\d){5}-(\\d){4}")
    private String telefone;

    @NotBlank(message = "Informe o número de matrícula do usuário")
    @Size(min = 7, message = "O número de matrícula deve conter no mínimo 7 caracteres")
    @Column(unique = true)
    private String matricula;

    @NotNull(message = "Informe o número do apartamento do usuário")
    private Integer apartamento;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AcessoUsuario> acessos;

    public Usuario(
            Long id,
            String nome,
            String email,
            String senha,
            String telefone,
            String matricula,
            Integer apartamento,
            List<AcessoUsuario> acessos
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.matricula = matricula;
        this.apartamento = apartamento;
        this.acessos = acessos;
    }

    public Usuario() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id)
                && Objects.equals(nome, usuario.nome)
                && Objects.equals(email, usuario.email)
                && Objects.equals(senha, usuario.senha)
                && Objects.equals(telefone, usuario.telefone)
                && Objects.equals(matricula, usuario.matricula)
                && Objects.equals(apartamento, usuario.apartamento)
                && Objects.equals(acessos, usuario.acessos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, telefone, matricula, apartamento, acessos);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public void setApartamento(Integer apartamento) {
        this.apartamento = apartamento;
    }

    public List<AcessoUsuario> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<AcessoUsuario> acessos) {
        this.acessos = acessos;
    }
}
