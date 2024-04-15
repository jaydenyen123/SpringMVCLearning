package guru.springframework.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class Customer {
    private final UUID id;
    private final String customerName;
    private final String version;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;
}
