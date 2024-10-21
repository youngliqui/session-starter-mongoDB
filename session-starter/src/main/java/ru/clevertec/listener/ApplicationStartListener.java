package ru.clevertec.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.clevertec.property.BlackListProvider;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {
    private final List<BlackListProvider> blackListProviders;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Blacklist properties: {}",
                blackListProviders.stream()
                        .flatMap(provider -> provider.getBlacklist().stream())
                        .collect(Collectors.toList())
        );
    }
}
