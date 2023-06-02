package sn.niit.restauranManagementApplication.domain;

import javax.persistence.*;
import javax.persistence.TemporalType;

import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
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
    private List<LineItem> lineItems;

    private String tokenSession;

    private State state;

    public Cart() {

    }

    public Cart(User user, String userSessionId) {
        this.tokenSession = userSessionId;
        this.user = user;
        this.state = State.ACTIVE;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        Double sum = 0.0;
        for (LineItem item : this.lineItems) {
            sum = sum + item.getProduct().getPrice();
        }
        return sum;
    }

    public int getItemCount() {
        return this.lineItems.size();
    }

    @Transactional
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> items) {
        this.lineItems = items;
    }

    public String getTokenSession() {
        return tokenSession;
    }

    public void setTokenSession(String tokenSession) {
        this.tokenSession = tokenSession;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
