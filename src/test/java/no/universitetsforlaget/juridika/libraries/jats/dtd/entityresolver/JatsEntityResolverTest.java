package no.universitetsforlaget.juridika.libraries.jats.dtd.entityresolver;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.endsWith;

import java.io.IOException;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class JatsEntityResolverTest {
  @Test
  public void resolve_jats_v1_1_dtd() throws SAXException, IOException {
    JatsEntityResolver resolver = new JatsEntityResolver();

    InputSource inputSource = resolver
        .resolveEntity("", "http://jats.nlm.nih.gov/publishing/1.1/JATS-journalpublishing1.dtd");

    assertThat(inputSource.getSystemId(), startsWith("file:/"));
    assertThat(inputSource.getSystemId(), endsWith("/dtd/niso-jats/1.1/JATS-journalpublishing1.dtd"));
  }

  @Test
  public void resolve_jats_v1_1d1_dtd() throws SAXException, IOException {
    JatsEntityResolver resolver = new JatsEntityResolver();

    InputSource inputSource = resolver
        .resolveEntity("", "http://jats.nlm.nih.gov/publishing/1.1d1/JATS-journalpublishing1.dtd");

    assertThat(inputSource.getSystemId(), startsWith("file:/"));
    assertThat(inputSource.getSystemId(), endsWith("/dtd/niso-jats/1.1d1/JATS-journalpublishing1.dtd"));
  }
}
