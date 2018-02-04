//package br.com.contabilizei.nfe;
//
//import br.com.contabilizei.dao.namespace.NamespaceContext;
//import br.com.contabilizei.estoque.enumeration.TipoMovimentacao;
//import br.com.contabilizei.estoque.model.ProcessoMovimentacaoEstoque;
//import br.com.contabilizei.nfe.emissao.enumeration.Uf;
//import br.com.contabilizei.nfe.emissao.model.Municipio;
//import br.com.contabilizei.nfe.emissao.model.NfeEmissao;
//import br.com.contabilizei.queue.facade.QueueFacade;
//import br.com.contabilizei.test.jersey.AbstractJerseyTest;
//import com.google.common.io.Resources;
//import com.sun.jersey.api.client.ClientResponse;
//import org.apache.http.HttpStatus;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Matchers;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import javax.ws.rs.core.MediaType;
//
//import java.nio.charset.Charset;
//
//import static org.junit.Assert.*;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(QueueFacade.class)
//public class NFeEmissaoJerseyTest extends AbstractJerseyTest {
//
//    private final String POST_CRIAR_NFE_IMPORTACAO = "nfe/criar/importada";
//
//    private final String ARQUIVO_PAYLOAD_CRIACAO_NFE_IMPORTACAO = "payload_criacao_nfe_importacao.json";
//
//    @Before
//    public void setUp() {
//        PowerMockito.mockStatic(QueueFacade.class);
//    }
//
//    @Test
//    public void criaNFeEmissao() throws Exception {
//        String jsonRequisicao = Resources
//                .toString(NFeEmissaoJerseyTest.class.getClassLoader().getResource(ARQUIVO_PAYLOAD_CRIACAO_NFE_IMPORTACAO), Charset.forName("UTF-8"));
//
//        final String chaveNFe = "35171120494125000122550010000000191018674596";
//
//        final ProcessoMovimentacaoEstoque processoEsperado = new ProcessoMovimentacaoEstoque(TipoMovimentacao.NFE_SAIDA);
//
//        final String cnpj = "20494125000122";
//
//        processoEsperado.setPayload("{\"ID_NFE\":\"2\",\"CNPJ_EMPRESA\":\"" + cnpj + "\"}");
//
//        processoEsperado.setId(Long.valueOf(5));
//
//        setBeforeRequest(() -> {
//            try {
//                criaMunicipioNiteroi();
//                criaMunicipioSP();
//
//                PowerMockito.doNothing().when(QueueFacade.class, "enfileirarProcesso", Matchers.eq(processoEsperado), Matchers.eq(cnpj));
//            } catch (Exception e) {
//                assertFalse(true);
//            }
//        });
//
//        ClientResponse resposta = resource()
//                .path(POST_CRIAR_NFE_IMPORTACAO)
//                .header("Content-type", MediaType.APPLICATION_JSON)
//                .header("Accept", MediaType.APPLICATION_JSON)
//                .post(ClientResponse.class, jsonRequisicao);
//
//        setAfterRequest(() -> {
//            PowerMockito.verifyStatic();
//            QueueFacade.enfileirarProcesso(Matchers.eq(processoEsperado), Matchers.eq(cnpj));
//
//            NamespaceContext.set(cnpj);
//
//            assertNotNull(NfeEmissao.getDAO().getByChave(chaveNFe));
//        });
//
//        assertEquals(HttpStatus.SC_OK, resposta.getStatus());
//    }
//
//    private void criaMunicipioNiteroi() {
//        Municipio municipio = new Municipio();
//        municipio.setCodIbge("3303302");
//        municipio.setNome("Niterói");
//        municipio.setUf(Uf.RJ);
//
//        Municipio.getDAO().update(municipio);
//    }
//
//    private void criaMunicipioSP() {
//        Municipio municipio = new Municipio();
//        municipio.setCodIbge("3550308");
//        municipio.setNome("São Paulo");
//        municipio.setUf(Uf.SP);
//
//        Municipio.getDAO().update(municipio);
//    }
//}