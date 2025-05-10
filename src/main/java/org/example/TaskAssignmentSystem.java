package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TaskAssignmentSystem {
    private List<ChefData> chefs = new ArrayList<>();

    public void loadChefs() {
        chefs.clear();
        chefs.add(new ChefData("Chef John", "Grill", 2, 5));
        chefs.add(new ChefData("Chef Lisa", "Grill", 3, 3));
        chefs.add(new ChefData("Chef Mike", "Pastry", 1, 2));
    }

    public ChefData assignTask(String expertise, String taskName) {
        Optional<ChefData> chef = chefs.stream()
                .filter(c -> c.getExpertise().equalsIgnoreCase(expertise) && c.isAvailable())
                .min(Comparator.comparingInt(ChefData::getCurrentTasks));

        if (chef.isPresent()) {
            chef.get().assignTask(taskName);
            return chef.get();
        } else {
            System.out.println("No available chef for expertise: " + expertise);
            return null;
        }
    }

    public boolean isChefOverloaded(String name) {
        return chefs.stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name) && c.getCurrentTasks() >= c.getMaxTasks());
    }

    public ChefData suggestAnotherChef(String expertise, String excludedName) {
        return chefs.stream()
                .filter(c -> c.getExpertise().equalsIgnoreCase(expertise) &&
                        !c.getName().equalsIgnoreCase(excludedName) && c.isAvailable())
                .min(Comparator.comparingInt(ChefData::getCurrentTasks))
                .orElse(null);
    }

    public int getChefMaxWorkload(String name) {
        return chefs.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .mapToInt(ChefData::getMaxTasks)
                .findFirst()
                .orElse(0);
    }

    public int getChefWorkload(String name) {
        return chefs.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .mapToInt(ChefData::getCurrentTasks)
                .findFirst()
                .orElse(0);
    }
}
