package com.github.jenya705.pancake;

import com.github.jenya705.pancake.service.PancakeService;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * Pancake manager
 *
 * @author Jenya705
 */
public class Pancake {

    private final Collection<PancakeService> services = new CopyOnWriteArrayList<>();

    /**
     *
     * Creating and put service if not already created
     *
     * @param service Service object
     * @return Service for this object
     */
    public PancakeService service(@NotNull Object service) {
        return findServiceByObject(service)
                .orElseGet(() -> {
                    PancakeService createdService = PancakeService.byAnnotations(service);
                    services.add(createdService);
                    return createdService;
                });
    }

    /**
     *
     * Finding service with name
     *
     * @param name Service name
     * @return Service with given name
     */
    public Optional<PancakeService> findService(String name) {
        return services
                .stream()
                .filter(it -> it.getName().equals(name))
                .findAny();
    }

    /**
     *
     * Finding service by service object
     *
     * @param service Service object
     * @return Service for given service object
     */
    public Optional<PancakeService> findServiceByObject(@NotNull Object service) {
        return findService(PancakeUtils.getName(service.getClass()));
    }

    /**
     *
     * Returns unmodifiable collection of services
     *
     * @return Unmodifiable collection of services
     */
    public Collection<PancakeService> getServices() {
        return Collections.unmodifiableCollection(services);
    }

}
