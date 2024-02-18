package com.fmatheus.app.controller.proxy.service.impl;

import com.fmatheus.app.config.properties.FeignLocationProperties;
import com.fmatheus.app.controller.dto.response.LocationResponse;
import com.fmatheus.app.controller.proxy.LocationProxy;
import com.fmatheus.app.controller.proxy.service.FeignLocationService;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class FeignLocationServiceImpl implements FeignLocationService {

    private final FeignLocationProperties properties;
    private final Decoder decoder;
    private final Encoder encoder;


    @Override
    public LocationResponse findByCep(String cep) {
        var url = String.format(properties.getResource().getFindCep(), cep);
        var proxy = this.builder(url);
        return proxy.findByCep();
    }


    /**
     * Cria configuracao Feign com informacoes de Timeout e url do servico.
     *
     * @return MxmResourceProxy
     * @author fernando.matheus
     */
    private LocationProxy builder(String url) {
        return Feign.builder()
                .encoder(this.encoder)
                .decoder(this.decoder)
                .target(LocationProxy.class, url);
    }
}
