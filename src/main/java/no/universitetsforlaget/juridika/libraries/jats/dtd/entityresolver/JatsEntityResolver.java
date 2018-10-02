package no.universitetsforlaget.juridika.libraries.jats.dtd.entityresolver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.sf.saxon.lib.StandardEntityResolver;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class JatsEntityResolver implements EntityResolver {
  private static final String JATS_V1_1D1_PATH = "/dtd/niso-jats/1.1d1/";
  private static final String JATS_V1_1_PATH = "/dtd/niso-jats/1.1/";

  private static final String JATS_V1_1D1_DTD_SYSTEM_ID =
      "http://jats.nlm.nih.gov/publishing/1.1d1/JATS-journalpublishing1.dtd";
  private static final String JATS_V1_1_DTD_SYSTEM_ID =
      "http://jats.nlm.nih.gov/publishing/1.1/JATS-journalpublishing1.dtd";

  public JatsEntityResolver() {
  }

  public InputSource resolveEntity(String publicId, String systemId)
      throws SAXException, IOException {
    if (JATS_V1_1D1_DTD_SYSTEM_ID.equals(systemId)) {
      final URL url = getClass().getResource(JATS_V1_1D1_PATH + "JATS-journalpublishing1.dtd");
      return loadResource(url);
    } else if (JATS_V1_1_DTD_SYSTEM_ID.equals(systemId)) {
      final URL url = getClass().getResource(JATS_V1_1_PATH + "JATS-journalpublishing1.dtd");
      return loadResource(url);
    } else if (systemId.startsWith("file:/")) {
      return loadResource(new URL(systemId));
    } else {
      return StandardEntityResolver.getInstance().resolveEntity(publicId, systemId);
    }
  }

  private InputSource loadResource(URL url) throws IOException {
    final InputStream inputStream = url.openStream();
    final InputSource source = new InputSource(inputStream);
    source.setSystemId(url.toString());
    return source;
  }
}
