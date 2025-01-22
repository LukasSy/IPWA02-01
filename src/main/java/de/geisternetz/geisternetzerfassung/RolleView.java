package de.geisternetz.geisternetzerfassung;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class RolleView {

    public List<Rolle> getAllRollen() {
        return Arrays.asList(Rolle.values());
    }
}
