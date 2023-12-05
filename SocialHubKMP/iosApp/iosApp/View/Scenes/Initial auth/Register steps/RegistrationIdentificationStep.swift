//
//  RegistrationIdentificationStep.swift
//  iosApp
//
//  Created by Jan Janovec on 23.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RegistrationIdentificationStep: View {
    @EnvironmentObject private var viewModel: RegisterVM
    var body: some View {
        VStack(alignment: .leading, spacing: 14) {
            HStack {
                VStack(alignment: .leading, spacing: 20) {
                    Text("Let's get to know each other")
                        .font(.largeTitle)
                        .fontWeight(.heavy)
                        .foregroundStyle(Color.white)
                        .padding(.top, 30)
                    Text("First of all tell us how to call you and get in touch with.")
                        .foregroundStyle(Color.gray)
                }
                
                Spacer()
            }
            VStack(alignment: .leading, spacing: 10) {
                Text("Your email*")
                    .foregroundStyle(Color.white)
                TextInputWrapper(
                    input: $viewModel.state.email.value,
                    placeholder: "Email",
                    background: .ultraThinMaterial,
                    foreground: .ultraThickMaterial,
                    icon: .init(systemName: "envelope"),
                    valid: viewModel.state.email.isValid,
                    errorPrompt: viewModel.state.email.validation.errorMessage
                )
            }
            VStack(alignment: .leading, spacing: 10) {
                Text("Your phone number*")
                    .foregroundStyle(Color.white)
                TextInputWrapper(
                    input: $viewModel.state.phoneNumber.value,
                    placeholder: "Phone number",
                    background: .ultraThinMaterial,
                    foreground: .ultraThickMaterial,
                    icon: .init(systemName: "phone"),
                    valid: viewModel.state.phoneNumber.isValid
                )
            }
            VStack(alignment: .leading, spacing: 10) {
                Text("Username*")
                    .foregroundStyle(Color.white)
                TextInputWrapper(
                    input: $viewModel.state.username.value,
                    placeholder: "Username",
                    background: .ultraThinMaterial,
                    foreground: .ultraThickMaterial,
                    icon: .init(systemName: "person"),
                    valid: viewModel.state.username.isValid,
                    errorPrompt: viewModel.state.username.validation.errorMessage
                )
            }
            Spacer()
        }
    }
}

#Preview {
    InitialAuthView()
}
