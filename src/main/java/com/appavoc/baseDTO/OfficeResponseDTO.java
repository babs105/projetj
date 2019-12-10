package com.appavoc.baseDTO;

public class OfficeResponseDTO {
	
	    private OfficeRequestDTO officeRequestDto ;
	    private String sessionCookie;

	    public OfficeResponseDTO(OfficeRequestDTO officeRequestDto, String sessionCookie) {
	        this.officeRequestDto = officeRequestDto;
	        this.sessionCookie = sessionCookie;
	    }

	    public OfficeResponseDTO() {
	    }

	  

	    public OfficeRequestDTO getOfficeRequestDto() {
			return officeRequestDto;
		}

		public void setOfficeRequestDto(OfficeRequestDTO officeRequestDto) {
			this.officeRequestDto = officeRequestDto;
		}

		public String getSessionCookie() {
	        return sessionCookie;
	    }

	    public void setSessionCookie(String sessionCookie) {
	        this.sessionCookie = sessionCookie;
	    }
}
