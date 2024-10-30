package atv.api.apiuser.service;

import atv.api.apiuser.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final ViaCepService viaCepService;

    @Autowired
    public AddressService(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    public Address getAddressByCep(String cep) {
        return viaCepService.findCep(cep);
    }

}
