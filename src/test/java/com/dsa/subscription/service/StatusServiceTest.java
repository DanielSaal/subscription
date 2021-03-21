package com.dsa.subscription.service;

import com.dsa.subscription.entity.Status;
import com.dsa.subscription.repository.StatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.dsa.subscription.util.MockUtils.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatusServiceTest {

    @InjectMocks
    StatusService service;

    @Mock
    StatusRepository repository;

    @Test
    public void whenExistingStatusName_thenReturnStatus() {

        when(repository.findByName(STATUS_NAME))
                .thenReturn(buildStatus());

        service.save(STATUS_NAME);

        verify(repository, times(1)).findByName(STATUS_NAME);
    }

    @Test
    public void whenNewStatusName_thenSaveStatusWithSuccess() {

        when(repository.findByName(NEW_STATUS_NAME))
                .thenReturn(null);
        when(repository.save(new Status(null, NEW_STATUS_NAME)))
                .thenReturn(buildNewStatus());

        service.save(NEW_STATUS_NAME);

        verify(repository, times(1)).findByName(NEW_STATUS_NAME);
        verify(repository, times(1)).save(new Status(null, NEW_STATUS_NAME));
    }
}
