//
//  RegisterVM.swift
//  iosApp
//
//  Created by Jan Janovec on 04.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

final class RegisterVM: RegisterViewModel, ObservableObject {
    @Published var state: RegisterContractState = .init(
        page: .identification,
        alert: .init(title: "", message: "", isPresented: false, actions: []),
        email: .init(value: "", validation: .email, triggerValidation: false, isMandatory: true),
        phoneNumber: .init(value: "", validation: .phoneNumber, triggerValidation: false, isMandatory: true),
        username: .init(value: "", validation: .username, triggerValidation: false, isMandatory: true),
        checkAccountAvailabilityState: .idle,
        firstName: .init(value: "", validation: nil, triggerValidation: false, isMandatory: true),
        lastName: .init(value: "", validation: nil, triggerValidation: false, isMandatory: true),
        gender: .init(value: "", validation: nil, triggerValidation: false, isMandatory: true),
        country: nil,
        registerState: .idle
    )
    
    override init() {
        super.init()
    }
}

extension RegisterVM {
    func nextStep() {
        self.setEvent(event: RegisterContractEventProceedToTheNextStep())
    }
    
    func edit() {
        self.setEvent(event: RegisterContractEventEdit())
    }
}
