//
//  InitialRegisterView.swift
//  iosApp
//
//  Created by Jan Janovec on 23.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct InitialRegisterView: View {
    @Binding var authPage: AuthPage
    @StateObject private var viewModel: RegisterVM = .init()
    @EnvironmentObject private var settings: GlobalSettingsVM
    var body: some View {
        ZStack {
            switch viewModel.state.page {
            case .identification:
                RegistrationIdentificationStep(authPage: $authPage)
            case .personalDetails:
                RegistrationPersonalStep()
            case .uploadPhoto:
                RegistrationUploadPhotoStep()
            case .confirmation:
                RegistrationConfirmStep()
            }
            VStack {
                Spacer()
                RoundedButton(text: viewModel.state.page == .confirmation ? "Confirm" : "Continue", foreground: Color.white, background: LinearGradient(colors: [.red, .orange], startPoint: .bottomLeading, endPoint: .topTrailing)) {
                    withAnimation {
                        viewModel.nextStep()
                    }
                }
                .disabled(!viewModel.currentPageIsValid)
            }
        }
        .environmentObject(viewModel)
        .alert(viewModel.state.alert.title, isPresented: $viewModel.state.alert.isPresented, actions: {
            ForEach(viewModel.state.alert.actions, id: \.self) { _ in
                Text("Action")
            }
        }, message: {
            Text(viewModel.state.alert.message)
        })
        .onChange(of: viewModel.state.registerState) { _, newValue in
            if newValue == .success {
                settings.refreshAccount()
            }
        }
    }
}

#Preview {
    InitialAuthView(currentAuthPage: .register)
}
