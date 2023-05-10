package sn.niit.restauranManagementApplication.domain;

import javax.persistence.*;
import javax.persistence.TemporalType;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "shoppingcart")
public class Cart
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long carIid;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Transient
    private Double totalPrice;

    @Transient
    private int itemNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<LineItem> items;

    private String tokenSession;

    public Long getId() {
        return carIid;
    }

    public void setId(Long id) {
        this.carIid = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice()
    {
        Double sum = 0.0;
        for (LineItem item :this.items)
        {
            sum= sum+ item.getProduit().getPrice();
        }
        return sum;
    }

    public int getItemNumber() {
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
}
