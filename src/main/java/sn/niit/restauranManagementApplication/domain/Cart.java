package sn.niit.restauranManagementApplication.domain;

import javax.persistence.*;
import javax.persistence.TemporalType;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "shoppingcart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @Transient
    private Double totalPrice;

    @Transient
    private int itemCount;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<LineItem> items;

    private String tokenSession;

    private Boolean active;

    public Cart() {

    }

    public Cart(User user, String userSessionId) {
        this.tokenSession = userSessionId;
        this.user = user;
        this.active = true;
    }

    public Long getId() {
        return cartId;
    }

    public void setId(Long id) {
        this.cartId = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        Double sum = 0.0;
        for (LineItem item : this.items) {
            sum = sum + item.getProduct().getPrice();
        }
        return sum;
    }

    public int getItemCount() {
        return this.items.size();
    }

    public Collection<LineItem> getItems() {
        return items;
    }

    public void setItems(Collection<LineItem> items) {
        this.items = items;
    }

    public String getTokenSession() {
        return tokenSession;
    }

    public void setTokenSession(String tokenSession) {
        this.tokenSession = tokenSession;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
