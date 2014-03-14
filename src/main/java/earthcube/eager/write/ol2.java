package earthcube.eager.write;

public class ol2 {
	
	public void writeRdfXml () {

	-- make sure that we don't duplicate people
	
	NSF as Organization
	CurrencyCodes
	PrincipalInvestigator
	-- list sub-orgs within NSF
			
	Person rdfs:subClassOf <Agent>
	performsAgentRole <AgentRole>
	hasCredential <Credential>
	hasPersonalInfoItem <PersonalInfoItem>
	
	PersonalInfoItem hasPersonalInfoType
	isPersonalInfoItemOf <Person>
	hasPersonalInfoValue <PersonalInfoValue>
		
	PersonalInfoItem hasPersonalInfoValue <PersonName>
	hasPersonalInfoType #person_name
	#person_name rdf:type PersonalInfoType
	hasAllowedValue <PersonName>
		
	PersonName rdfs:subClassOf <PersonalInfoValue>
	fullNameAsString String
	firstOrGivenName String
	familyOrSurname String
		
	AgentRole has AgentRoleType
	AgentRole isPerformedBy <Agent>
	isAgentRoleIn <FundingAward>
	(AgentRole)
	ol:isPerformedBy ol:tomNarock. (Agent)
	ol:isAgentRoleIn ol:OceanLink. (FundingAward)
	_:x ol:hasAgentRoleType ol:PI.

	-- model AGU as organization and meetings as events? are abstracts programs or documentSets?
			
}