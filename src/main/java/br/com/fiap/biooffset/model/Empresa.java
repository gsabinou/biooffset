package br.com.fiap.biooffset.model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "bio_empresas")
public class Empresa implements UserDetails {

    @Id
    @Column(name = "id_empresa")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="SEQ_EMPRESAS"
    )
    @SequenceGenerator(
            name = "SEQ_EMPRESAS",
            sequenceName = "SEQ_EMPRESAS",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "raz_social")
    private String razaoSocial;

    @Column(name = "nr_cnpj")
    private String cnpj;

    @Column(name = "ds_senha")
    private String senha;

    @Column(name = "tip_empresa")
    @Enumerated(EnumType.STRING)
    private EmpresaRole role;

    @OneToMany(mappedBy = "empresaDesenvolvedora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Projeto> projetos = new ArrayList<>();

    @OneToMany(mappedBy = "empresaCompradoraId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacoes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == EmpresaRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_COMPRADOR"),
                    new SimpleGrantedAuthority("ROLE_DESENVOLVEDOR")
            );
        }

        else if (this.role == EmpresaRole.DESENVOLVEDOR) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_DESENVOLVEDOR"),
                    new SimpleGrantedAuthority("ROLE_COMPRADOR")
            );
        }

        return List.of(new SimpleGrantedAuthority("ROLE_COMPRADOR"));
    }

    @Override
    public @Nullable String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.cnpj;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    //NOTA: o ideal é fazer as verificações acima por meio de uma coluna booleana no bd.
    // Estou usando essas classes apenas para praticar e aprender.
    // Todos os métodos por padrão estão definidos como true.
}

