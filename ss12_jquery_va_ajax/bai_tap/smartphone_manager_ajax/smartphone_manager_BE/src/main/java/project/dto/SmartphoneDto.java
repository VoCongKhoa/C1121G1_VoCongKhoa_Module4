package project.dto;

import javax.validation.constraints.NotBlank;

public class SmartphoneDto {
    private Long id;
    @NotBlank
    private String producer;
    @NotBlank (message = "abc")
    private String model;

    private double price;

    public SmartphoneDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
