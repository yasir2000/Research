Public int eventID = 1;
public void WriteQuestion(string nbloID, object Result, string type)
{
xw.WriteStartElement("event");
xw.WriteAttributeString("type", "Question");
xw.WriteAttributeString("flowID", eventID.ToString());
xw.WriteStartElement("actuator");
xw.WriteAttributeString("type", "query");
xw.WriteStartElement("action");
xw.WriteAttributeString("executorNBLO", nbloID);
xw.WriteAttributeString("result", Result.ToString());
xw.WriteEndElement();
xw.WriteEndElement();
xw.WriteEndElement();
eventID++;
}