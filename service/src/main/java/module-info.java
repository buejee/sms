module sms.service{
	requires sms.model;
	requires sms.persistence;
	uses sms.persistence.PersistenceService;
	provides sms.service.StudentService with sms.service.impl.StudentServiceImpl;
	exports sms.service;
	exports sms.service.impl;
}