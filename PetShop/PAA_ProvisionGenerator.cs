public class ProvisionGenerator
{
private XmlTextWriter xw;
public Provision_Generator(string fileName)
{
if (fileName == "")
{
fileName = "c:\\Provision.xml";
}
xw = new XmlTextWriter(fileName, Encoding.UTF8);
xw.Formatting = Formatting.Indented;
xw.WriteProcessingInstruction("xmlstylesheet","type=\"text/xsl\"
href=\"http://cloud.cms.livjm.ac.uk/xml/PAANeptune/xsl/provision.xsl\
"");
xw.WriteStartElement("ProvisionModel");
}
}