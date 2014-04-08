package earthcube.eager.write;

public class ol2 {
	
	public void writeRdfXml () {

	-- make sure that we don't duplicate people
	
	NSF as Organization
	CurrencyCodes
	PrincipalInvestigator
	-- list sub-orgs within NSF
			
	(AgentRole)
	ol:isPerformedBy ol:tomNarock. (Agent)
	ol:isAgentRoleIn ol:OceanLink. (FundingAward)
	_:x ol:hasAgentRoleType ol:PI.

	-- model AGU as organization and meetings as events? are abstracts programs or documentSets?

			-- add if values exist statements
			-- email Bob, Adam re: waiting for paper
}