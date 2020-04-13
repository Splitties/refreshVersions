package de.fayard.versions

import de.fayard.versions.internal.parseVersionsFromMavenMetaData
import org.gradle.internal.impldep.junit.framework.Assert.assertEquals
import org.gradle.internal.impldep.junit.framework.Assert.assertTrue
import org.junit.jupiter.api.Test

class MavenMetaDataParserTest {
    @Test
    fun testParser() {
        val xml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <metadata>
              <groupId>org.jetbrains.kotlin</groupId>
              <artifactId>kotlin-compiler</artifactId>
              <version>1.3.71</version>
              <versioning>
                <latest>1.3.71</latest>
                <release>1.3.71</release>
                <versions>
                  <version>0.0.1-test-deploy</version>
                  <version>0.0.2-test-deploy</version>
                  <version>0.5.998</version>
                  <version>0.5.1131</version>
                  <version>0.6.22</version>
                  <version>0.6.31</version>
                  <version>0.6.69</version>
                  <version>0.6.179</version>
                  <version>0.6.317</version>
                  <version>0.6.350</version>
                  <version>0.6.594</version>
                  <version>0.6.602</version>
                  <version>0.6.786</version>
                  <version>0.6.800</version>
                  <version>0.6.912</version>
                  <version>0.6.1070</version>
                  <version>0.6.1315</version>
                  <version>0.6.1411</version>
                  <version>0.6.1507</version>
                  <version>0.6.1590</version>
                  <version>0.6.1603</version>
                  <version>0.6.1617</version>
                  <version>0.6.1658</version>
                  <version>0.6.1670</version>
                  <version>0.6.1673</version>
                  <version>0.6.1910</version>
                  <version>0.6.2451</version>
                  <version>0.6.2517</version>
                  <version>0.7.5</version>
                  <version>0.7.86</version>
                  <version>0.7.115</version>
                  <version>0.7.191</version>
                  <version>0.7.258</version>
                  <version>0.7.270</version>
                  <version>0.7.271</version>
                  <version>0.7.802</version>
                  <version>0.7.852</version>
                  <version>0.7.895</version>
                  <version>0.7.1090</version>
                  <version>0.7.1217</version>
                  <version>0.8.11</version>
                  <version>0.8.484</version>
                  <version>0.8.679</version>
                  <version>0.8.1527</version>
                  <version>0.9.66</version>
                  <version>0.9.206</version>
                  <version>0.9.976</version>
                  <version>0.10.4</version>
                  <version>0.10.195</version>
                  <version>0.10.709</version>
                  <version>0.10.770</version>
                  <version>0.10.1316</version>
                  <version>0.11.91</version>
                  <version>0.11.91.1</version>
                  <version>0.11.91.2</version>
                  <version>0.11.91.4</version>
                  <version>0.12.200</version>
                  <version>0.12.213</version>
                  <version>0.12.412</version>
                  <version>0.12.613</version>
                  <version>0.12.1218</version>
                  <version>0.12.1230</version>
                  <version>0.13.1513</version>
                  <version>0.13.1514</version>
                  <version>0.13.1516</version>
                  <version>0.14.449</version>
                  <version>0.14.451</version>
                  <version>1.0.0-beta-1038</version>
                  <version>1.0.0-beta-1103</version>
                  <version>1.0.0-beta-2417</version>
                  <version>1.0.0-beta-2422</version>
                  <version>1.0.0-beta-2423</version>
                  <version>1.0.0-beta-3593</version>
                  <version>1.0.0-beta-3594</version>
                  <version>1.0.0-beta-3595</version>
                  <version>1.0.0-beta-4583</version>
                  <version>1.0.0-beta-4584</version>
                  <version>1.0.0-beta-4589</version>
                  <version>1.0.0-rc-1036</version>
                  <version>1.0.0</version>
                  <version>1.0.1</version>
                  <version>1.0.1-1</version>
                  <version>1.0.1-2</version>
                  <version>1.0.2</version>
                  <version>1.0.2-1</version>
                  <version>1.0.3</version>
                  <version>1.0.4</version>
                  <version>1.0.5</version>
                  <version>1.0.5-2</version>
                  <version>1.0.5-3</version>
                  <version>1.0.6</version>
                  <version>1.0.7</version>
                  <version>1.1.0</version>
                  <version>1.1.1</version>
                  <version>1.1.2</version>
                  <version>1.1.2-2</version>
                  <version>1.1.2-3</version>
                  <version>1.1.2-4</version>
                  <version>1.1.2-5</version>
                  <version>1.1.3</version>
                  <version>1.1.3-2</version>
                  <version>1.1.4</version>
                  <version>1.1.4-2</version>
                  <version>1.1.4-3</version>
                  <version>1.1.50</version>
                  <version>1.1.51</version>
                  <version>1.1.60</version>
                  <version>1.1.61</version>
                  <version>1.2.0</version>
                  <version>1.2.10</version>
                  <version>1.2.20</version>
                  <version>1.2.21</version>
                  <version>1.2.30</version>
                  <version>1.2.31</version>
                  <version>1.2.40</version>
                  <version>1.2.41</version>
                  <version>1.2.50</version>
                  <version>1.2.51</version>
                  <version>1.2.60</version>
                  <version>1.2.61</version>
                  <version>1.2.70</version>
                  <version>1.2.71</version>
                  <version>1.3.0-rc-190</version>
                  <version>1.3.0-rc-198</version>
                  <version>1.3.0</version>
                  <version>1.3.10</version>
                  <version>1.3.11</version>
                  <version>1.3.20</version>
                  <version>1.3.21</version>
                  <version>1.3.30</version>
                  <version>1.3.31</version>
                  <version>1.3.40</version>
                  <version>1.3.41</version>
                  <version>1.3.50</version>
                  <version>1.3.60</version>
                  <version>1.3.61</version>
                  <version>1.3.70</version>
                  <version>1.3.71</version>
                </versions>
                <lastUpdated>20200323122144</lastUpdated>
              </versioning>
            </metadata>
        """.trimIndent()

        val versions = parseVersionsFromMavenMetaData(xml)

        assertEquals(versions.size, 138)
        assertEquals(versions[0].value, "0.0.1-test-deploy")
        assertEquals(versions[137].value, "1.3.71")
    }
}
