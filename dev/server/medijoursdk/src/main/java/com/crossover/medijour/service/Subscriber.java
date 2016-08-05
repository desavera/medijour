package com.crossover.medijour.service;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subscriber database table.
 * 
 */
@Entity
@Table(name="subscriber")
@NamedQuery(name="Subscriber.findAll", query="SELECT s FROM Subscriber s")
public class Subscriber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	@Column(name="provider_unique")
	private String providerUnique;

	//bi-directional many-to-one association to Subscription
	@OneToMany(mappedBy="subscriber")
	private List<Subscription> subscriptions;

	public Subscriber() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProviderUnique() {
		return this.providerUnique;
	}

	public void setProviderUnique(String providerUnique) {
		this.providerUnique = providerUnique;
	}

	public List<Subscription> getSubscriptions() {
		return this.subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Subscription addSubscription(Subscription subscription) {
		getSubscriptions().add(subscription);
		subscription.setSubscriber(this);

		return subscription;
	}

	public Subscription removeSubscription(Subscription subscription) {
		getSubscriptions().remove(subscription);
		subscription.setSubscriber(null);

		return subscription;
	}

}