package no.universitetsforlaget.juridika.libraries.jats.dtd.entityresolver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.sf.saxon.lib.StandardEntityResolver;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class JatsEntityResolver implements EntityResolver {
  private static final String DTD_PUBLISHING_ENTRYPOINT = "JATS-journalpublishing1.dtd";
  private static final String DTD_ARCHIVING_ENTRYPOINT = "JATS-archivearticle1.dtd";

  private static final String JATS_PUBLISHING_V1_1D1_PATH = "/dtd/niso-jats/1.1d1/";
  private static final String JATS_PUBLISHING_V1_1_PATH = "/dtd/niso-jats/1.1/";
  private static final String JATS_ARCHIVING_V1_2_PATH = "/dtd/archiving/1.2/";

  // `d` stands for draft, so 1.1d1 refers to a draft version
  private static final String JATS_PUBLISHING_V1_1D1_DTD_SYSTEM_ID =
      "http://jats.nlm.nih.gov/publishing/1.1d1/" + DTD_PUBLISHING_ENTRYPOINT;
  private static final String JATS_PUBLISHING_V1_1_DTD_SYSTEM_ID =
      "http://jats.nlm.nih.gov/publishing/1.1/" + DTD_PUBLISHING_ENTRYPOINT;
  private static final String JATS_ARCHIVING_V1_2_SYSTEM_ID =
      "http://jats.nlm.nih.gov/archiving/1.2/" + DTD_ARCHIVING_ENTRYPOINT;

  private static final String JATS_PUBLISHING_V1_1_PUBLIC_ID =
      "-//NLM//DTD JATS (Z39.96) Journal Publishing DTD v1.1 20151215//EN";
  private static final String JATS_ARCHIVING_V1_2_PUBLIC_ID =
      "-//NLM//DTD JATS (Z39.96) Journal Archiving and Interchange DTD v1.2 20190208//EN";

  public JatsEntityResolver() {
  }

  public InputSource resolveEntity(String publicId, String systemId)
      throws SAXException, IOException {
    if (JATS_PUBLISHING_V1_1D1_DTD_SYSTEM_ID.equals(systemId)) {
      final URL url = getClass().getResource(JATS_PUBLISHING_V1_1D1_PATH + DTD_PUBLISHING_ENTRYPOINT);
      return loadResource(url);
    } else if (JATS_PUBLISHING_V1_1_DTD_SYSTEM_ID.equals(systemId) || JATS_PUBLISHING_V1_1_PUBLIC_ID.equals(publicId)) {
      final URL url = getClass().getResource(JATS_PUBLISHING_V1_1_PATH + DTD_PUBLISHING_ENTRYPOINT);
      return loadResource(url);
    } else if (JATS_ARCHIVING_V1_2_SYSTEM_ID.equals(systemId) || JATS_ARCHIVING_V1_2_PUBLIC_ID.equals(publicId)) {
      final URL url = getClass().getResource(JATS_ARCHIVING_V1_2_PATH + DTD_ARCHIVING_ENTRYPOINT);
      return loadResource(url);
    }
    else if (systemId.startsWith("file:/")) {
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
